package com.perfectcomputersolutions.pos.crud.controller

import com.perfectcomputersolutions.pos.crud.service.ProductService
import com.perfectcomputersolutions.pos.model.Product
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

/**
 * REST controller for API calls related to products.
 *
 * @see Product
 */
@RestController
@RequestMapping("/product")
class ProductController {

    private static final Logger Log = LoggerFactory.getLogger(ProductController.class)

    @Autowired
    ProductService service

    @PostMapping
    def create(@RequestBody Product product) {

        Log.info("Receive new POST request")

        def message = ("Product successfully created")
        def body    = new HashMap<>()

        body.put("message", message)
        body.put("product", service.create(product))

        return new ResponseEntity(body, HttpStatus.ACCEPTED)
    }

    @GetMapping
    def findAll() {

        Log.info("Received new GET request")

        def body     = new HashMap<>()
        def entities = service.findAll()
        def message  = "Entities successfully retrieved"

        body.put("message",  message)
        body.put("entities", entities)
        body.put("count",    entities.size())

        Log.info(message)

        return new ResponseEntity<>(body, HttpStatus.OK)
    }

}
