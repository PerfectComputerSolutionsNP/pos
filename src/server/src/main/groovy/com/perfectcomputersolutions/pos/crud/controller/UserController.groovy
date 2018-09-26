package com.perfectcomputersolutions.pos.crud.controller

import com.perfectcomputersolutions.pos.crud.service.UserService
import com.perfectcomputersolutions.pos.model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller for API calls related to users.
 *
 * @see User
 */
@RestController
@RequestMapping("/user")
class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class)

    @Autowired
    UserService service

    @PostMapping
    def create(@RequestBody User user) {

        log.info("Received new POST request")

        def message = "User successfully created"
        def body    = new HashMap<>()

        body.put("message", message)
        body.put("user", service.create(user))

        log.info(message)

        return new ResponseEntity(body, HttpStatus.ACCEPTED)
    }

    @GetMapping
    def findAll() {

        log.info("Received new GET request")

        def body     = new HashMap<>()
        def entities = service.findAll()
        def message  = "Entities successfully retrieved"

        body.put("message",  message)
        body.put("entities", entities)
        body.put("count",    entities.size())

        log.info(message)

        HttpHeaders headers = new HttpHeaders()
//        headers.set("Access-Control-Allow-Origin", "*")

        return new ResponseEntity(body, headers, HttpStatus.ACCEPTED)
    }

}