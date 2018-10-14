package com.perfectcomputersolutions.pos.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthCheckController {

    @GetMapping
    def handleHealthCheck() {

        def body = [ "message" : "I'm a teapot"]

        return new ResponseEntity<?>(body, HttpStatus.OK)
    }
}
