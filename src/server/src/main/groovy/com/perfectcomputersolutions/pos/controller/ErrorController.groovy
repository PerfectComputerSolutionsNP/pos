package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.exception.CrudException
import com.perfectcomputersolutions.pos.exception.DuplicateEntityException
import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.exception.ValidationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import javax.servlet.http.HttpServletRequest

import static org.springframework.http.HttpStatus.CONFLICT
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import static org.springframework.http.HttpStatus.NOT_FOUND

/**
 * Global exception handler that sends the appropriate HTTP status code
 * and message depending on the type of Exception thrown. Exception handling
 * is done in two ways. We have anticipated exceptions and unanticipated
 * exceptions.
 *
 * <p>
 *
 * All anticipated exceptions extend {@code CrudException}. If a
 * {@code CrudException} is thrown, then we will then detect the specific type
 * of Exception (by subclass) and pick the appropriate HTTP status code. The
 * exception's message attribute will then be used as the message that is returned
 * to the user in the {@code message} field of the JSON response body.
 *
 * <p>
 *
 * Unanticipated exceptions do not have their message attribute exposed to the user
 * as there may be sensitive data or information that the exception injects automatically.
 * Instead, a default generic message is sent to the user that notifies them to contact the
 * administrator. Ideally, this should never run. The error will be logged and we
 * can use these logs to help debug and fix the error such that the exception is
 * not thrown again. The default status code for unanticipated exceptions is 500 to
 * indicate that an internal server error has occurred.
 */
@ControllerAdvice
class ErrorController {

    private static final Logger log = LoggerFactory.getLogger(ErrorController.class)

    static final String VIOLATIONS = "violations"

//    @ExceptionHandler(value = Exception.class)
    def invalid(HttpServletRequest req, Exception ex) {

        log.error("Request: " + req.method + " to " + req.requestURL + " raised " + ex.class.name, ex)

        def body
        def status
        def message

        body = new HashMap<>()

        if (ex instanceof CrudException) {

            switch (ex) {

                case DuplicateEntityException:
                    status = CONFLICT
                    break

                case NoSuchEntityException:
                    status = NOT_FOUND
                    break

                case ValidationException:
                    status = NOT_ACCEPTABLE
                    ex     = (ValidationException) ex

                    body.put(VIOLATIONS, ex.violations)

                    break

                default:
                    status = INTERNAL_SERVER_ERROR
            }

            message = ex.message

        } else {

            log.error("Unexpected exception thrown", ex)

            status  = INTERNAL_SERVER_ERROR
            message = "An unexpected error occurred, please contact your administrator"
        }

        body.put(CrudController.MESSAGE, message)

        return new ResponseEntity(body, status)
    }

}