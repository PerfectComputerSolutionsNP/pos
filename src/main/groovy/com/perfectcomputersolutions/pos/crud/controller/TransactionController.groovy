package com.perfectcomputersolutions.pos.crud.controller

import com.perfectcomputersolutions.pos.crud.service.TransactionService
import com.perfectcomputersolutions.pos.model.Transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transaction")
class TransactionController {

    private static final Logger Log = LoggerFactory.getLogger(TransactionController.class)

    @Autowired
    TransactionService service

    @PostMapping
    def create(@RequestBody Transaction transaction) {

        Log.info("Received POST request")

        def message = "Transaction successfully created"
        def body = new HasMap<>()

        body.put("message", message)
        body.put("transaction", service.create(transaction))
    }

    @GetMapping
    def findAll() {

        Log.info("Received new GET Request")

        def body     = new HasMap<>()
        def entities = service.findAll()
        def message  = "Entities successfully retrieved"

        body.put("message", message)
        body.put("entities", entities)
        body.put("count", entities.size())

        Log.info(message)

        return new ResponseEntity<>(body, HttpStatus.OK)
    }
}
