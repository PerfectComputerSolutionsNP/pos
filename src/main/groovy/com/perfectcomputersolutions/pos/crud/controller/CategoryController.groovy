package com.perfectcomputersolutions.pos.crud.controller

import com.perfectcomputersolutions.pos.crud.service.CategoryService
import com.perfectcomputersolutions.pos.model.Category

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
 * REST controller for API calls related to categories.
 *
 * @see Category
 */
@RestController
@RequestMapping("/category")
class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class)

    @Autowired
    CategoryService service

    @PostMapping
    def create(@RequestBody Category category) {

        log.info("Received New Post Request")

        def message = ("Category successfully created")
        def body    = new HashMap<>()

        body.put("message", message)
        body.put("category", service.create(category))

        log.info(message)

        return new ResponseEntity(body, HttpStatus.ACCEPTED)
    }

    @GetMapping
    def findAll() {

        log.info("Received bew GET Request")

        def body     = new HashMap<>()
        def entities = service.findAll()
        def message  = ("Entities successfully retrieved")

        body.put("message", message)
        body.put("entities", entities)
        body.put "count", entities.size()

        log.info(message)

        return new ResponseEntity<>(body, HttpStatus.OK)
    }
}
