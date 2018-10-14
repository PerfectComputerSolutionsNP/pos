package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.exception.CaughtException
import com.perfectcomputersolutions.pos.exception.MalformedRequestException
import com.perfectcomputersolutions.pos.utility.BugRecorder
import com.perfectcomputersolutions.pos.exception.ThrownException
import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.exception.ValidationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import javax.servlet.http.HttpServletRequest

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import static org.springframework.http.HttpStatus.NOT_FOUND

@ControllerAdvice
class ErrorController {

    // https://stackoverflow.com/questions/48991353/how-to-catch-all-unhandled-exceptions-without-exceptionhandler-in-spring-mvc

    private static final Logger log = LoggerFactory.getLogger(ErrorController.class)

    static final String VIOLATIONS = "violations"

    // MissingServletRequestParameterException

    @ExceptionHandler(Exception.class)
    def handleException(HttpServletRequest req, Exception ex) {

        log.error("${req.method} request to ${req.requestURL} threw ${ex.class.simpleName}", ex)

        final Map<?, ?>  body
        final HttpStatus status
        final String     message

        body = new HashMap<>()

        // All exceptions that are explicitly thrown
        // by the API that are NOT wrappers around previously
        // thrown (anticipated) exceptions should extend CrudException
        if (ex instanceof ThrownException) {

            switch (ex) {

                case NoSuchEntityException:
                    status = NOT_FOUND
                    break

                case ValidationException:
                    status = NOT_ACCEPTABLE
                    ex     = (ValidationException) ex

                    body.put(VIOLATIONS, ex.violations)
                    break

                case MalformedRequestException:
                    status = BAD_REQUEST
                    break

                default:
                    status = INTERNAL_SERVER_ERROR
                    break
            }

            message = ex.message

        // All other exceptions that were anticipated, caught, and
        // re-thrown should be an instance of the CaughtException class
        } else if (ex instanceof CaughtException) {

            Throwable cause = ex.cause

            switch (cause) {

                case DataIntegrityViolationException:
                    status = NOT_ACCEPTABLE
                    break

                case IllegalArgumentException:
                    status = INTERNAL_SERVER_ERROR
                    break

                default:
                    status = INTERNAL_SERVER_ERROR
                    break
            }

            message = ex.message

        // All other Exceptions that are caught will be considered
        // bugs as they were not thrown or expected by the developer.
        // This block returns a generic error message with a 500 status
        // and the internal server error is be logged and reported
        // to the administrator.
        } else {

            BugRecorder.record(ex)

            status  = INTERNAL_SERVER_ERROR
            message = "An unexpected error occurred, please contact your administrator"
        }

        body.put("message",   message)
        body.put("timestamp", new Date())
        body.put("status",    status.value())
        body.put("error",     status.reasonPhrase)
        body.put("exception", ex.class)

        return CrudController.respond(body, status)
    }
}