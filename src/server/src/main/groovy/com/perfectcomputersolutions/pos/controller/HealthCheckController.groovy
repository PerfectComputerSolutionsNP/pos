package com.perfectcomputersolutions.pos.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
@Api(value="category", description='A simple controller with a GET mapping for health check purposes in testing.')
class HealthCheckController {

    @GetMapping
    @ApiOperation(value = "Returns a simple message and a 200 to indicated the API is online. This operation does not requires any role.")
    def handleHealthCheck() {

        def body = [ "message" : "I'm a teapot"]

        return new ResponseEntity<?>(body, HttpStatus.OK)
    }
}
