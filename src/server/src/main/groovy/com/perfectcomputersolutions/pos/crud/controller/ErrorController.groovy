package com.perfectcomputersolutions.pos.crud.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import javax.servlet.http.HttpServletRequest

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR

@ControllerAdvice
class ErrorController {

    private static final Logger log = LoggerFactory.getLogger(ErrorController.class)

//----------------------------------------------------------------------------------------------------------------------

    @ExceptionHandler(value = Exception.class)
    def error(HttpServletRequest req, Exception ex) {

        def message = "Request: " + req.getRequestURL() + " raised " + ex.getClass().getName()

        log.error(message, ex)

        def body = { ->

            def map = new HashMap<>()

            map.put("message", ex.message)
            map.put("exceptionType", ex.getClass().getName())

            return map
        }

        return new ResponseEntity(body(), INTERNAL_SERVER_ERROR)
    }

}