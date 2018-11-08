package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.factory.NotificationFactory
import com.perfectcomputersolutions.pos.payload.Batch
import com.perfectcomputersolutions.pos.service.EmailService
import com.perfectcomputersolutions.pos.payload.SimpleMessage
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email")
@Api(value="category",
        description='Operations pertaining to emails. From here emails can be sent, viewed, or deleted by a user with ADMIN rights.')
class EmailController {

    @Autowired EmailService        service
    @Autowired NotificationFactory factory

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Send a simple email message. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def send(@RequestBody SimpleMessage message) {

        def email = factory.getEmail(message)

        service.deliver(email)

        def body = [

                (CrudController.MESSAGE) : "Message received and will be sent",
                "original" : message
        ]

        CrudController.respond(body, HttpStatus.ACCEPTED)
    }
}
