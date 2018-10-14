package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.service.EmailService
import com.perfectcomputersolutions.pos.utility.IdBatch
import com.perfectcomputersolutions.pos.utility.SimpleMessage
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
class EmailController {

    @Autowired EmailService service

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    def findById(@PathVariable Long id) {

        def body = [

                (CrudController.CONTENT) : service.findById(id)
        ]

        CrudController.respond(body, HttpStatus.OK)
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    def findAll(
            @RequestParam int page,
            @RequestParam int size) {

        def body = service.findAll(page, size)

        CrudController.respond(body, HttpStatus.OK)
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    def send(@RequestBody SimpleMessage message) {

        service.sendEmail(
                message.to ,
                message.subject,
                message.text
        )

        def body = [

                (CrudController.MESSAGE) : "Message received and will be sent",
                "original" : message
        ]

        CrudController.respond(body, HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    def deleteById(@PathVariable Long id) {

        service.deleteById(id)

        def body = [

                (CrudController.MESSAGE) : "Email deleted"
        ]

        CrudController.respond(body, HttpStatus.OK)
    }

    @DeleteMapping("/batch")
    @PreAuthorize("hasRole('ADMIN')")
    def deleteByIds(@RequestBody IdBatch<Long> ids) {

        service.deleteByIds(ids)

        def body = [

                (CrudController.MESSAGE) : "Success"
        ]

        CrudController.respond(body, HttpStatus.OK)
    }
}
