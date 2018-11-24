package com.perfectcomputersolutions.pos.controller

import com.fasterxml.jackson.core.JsonProcessingException
import com.perfectcomputersolutions.pos.exception.CaughtException
import com.perfectcomputersolutions.pos.exception.MalformedRequestException
import com.perfectcomputersolutions.pos.exception.ThrownException
import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.exception.ValidationException
import org.hibernate.HibernateException
import org.hibernate.exception.DataException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import javax.persistence.PersistenceException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import java.sql.Timestamp

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.FORBIDDEN
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import static org.springframework.http.HttpStatus.NOT_FOUND

@ControllerAdvice
class ErrorController {

    // TODO - http://www.springboottutorial.com/spring-boot-exception-handling-for-rest-services
    // https://www.toptal.com/java/spring-boot-rest-api-error-handling
    // https://stackoverflow.com/questions/48991353/how-to-catch-all-unhandled-exceptions-without-exceptionhandler-in-spring-mvc

    private static final Logger log = LoggerFactory.getLogger(ErrorController.class)

    static final String ERRORS = "errors"

    // TODO - Read in as an environment variable
//    @Value('${administrator.email}')
    String adminEmail = "perfectcomputersolutionsnp@gmail.com"

    @ExceptionHandler(Exception.class)
    def handleException(HttpServletRequest req, Exception ex) {

        log.error("${req.method} request to ${req.requestURL} threw a(n) ${ex.class.simpleName}", ex)

        final Map<?, ?>  body
        final HttpStatus status
        final String     message

        body = new HashMap<>()

        // All exceptions that are explicitly thrown
        // by the API that are NOT wrappers around previously
        // thrown (anticipated) exceptions should extend ThrownException
        if (ex instanceof ThrownException) {

            switch (ex) {

                case NoSuchEntityException:
                    status = NOT_FOUND
                    break

                case ValidationException:
                    status = NOT_ACCEPTABLE
                    ex     = (ValidationException) ex

                    body.put(ERRORS, ex.violations)
                    break

                case MalformedRequestException:
                    status = BAD_REQUEST
                    break

                default:
                    status = BAD_REQUEST
                    break
            }

        // All other exceptions that were anticipated, caught, and possibly
        // re-thrown should be an instance of the CaughtException class
        } else if (ex instanceof CaughtException) {

            def cause = ex.cause

            switch (cause) {

                case DataAccessException:
                    status = NOT_ACCEPTABLE
                    break

                case JsonProcessingException:
                case IllegalArgumentException:
                case IOException:

                default:
                    status = BAD_REQUEST
                    break
            }

        // All other exceptions are either thrown by the
        // framework during the lifecycle of a request, and
        // therefore cannot be caught within a try-catch block,
        // or are unexpected and thus, considered bugs. Common
        // Spring exceptions are handled with the appropriate
        // HTTP response code. All other exceptions result in a
        // 500 and a generic "Contact your administrator" message.
        } else {

            switch (ex) {

                case ServletException:
                case HibernateException:
                case PersistenceException:
                case HttpMessageConversionException:
                case DataException:
                    status  = BAD_REQUEST
                    break

                case AccessDeniedException:
                    status  = FORBIDDEN
                    break

                default:
                    status  = INTERNAL_SERVER_ERROR
            }
        }

        body.put("message",   ex.message)
        body.put("timestamp", new Timestamp(System.currentTimeMillis()))
        body.put("status",    status.value())
        body.put("reason",    status.reasonPhrase)
        body.put("exception", ex.class.simpleName)

        return CrudController.respond(body, status)
    }
}