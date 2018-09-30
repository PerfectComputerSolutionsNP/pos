package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.service.CrudService
import com.perfectcomputersolutions.pos.model.ModelEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

/**
 * Abstract REST controller that facilitates CRUD operations for basic entities.
 *
 * @param <T> Generic ModelEntity type.
 * @param <ID> Id type.
 */
@CrossOrigin
abstract class CrudController<T extends ModelEntity, ID extends Serializable> {

    private static final Logger log = LoggerFactory.getLogger(CrudController.class)

    static final String ENTITIES = "entities"
    static final String ENTITY   = "entity"
    static final String MESSAGE  = "message"

    abstract CrudService<T, ID> getService()

    @GetMapping
    def findAll() {

        log.info("Received new GET request")

        def body     = new HashMap<>()
        def entities = service.findAll()
        def message  = "Entities successfully retrieved"

        body.put(MESSAGE,  message)
        body.put(ENTITIES, entities)

        log.info(message)

        return new ResponseEntity(body, HttpStatus.ACCEPTED)
    }

    @GetMapping("/{id}")
    def findById(@PathVariable ID id) {

        log.info("Received new GET request")

        def body    = new HashMap<>()
        def entity  = service.findById(id)
        def message = "Entity successfully retrieved"

        body.put(MESSAGE, message)
        body.put(ENTITY,  entity)

        log.info(message)

        return new ResponseEntity(body, HttpStatus.ACCEPTED)
    }

    @PostMapping
    def save(@RequestBody T entity) {

        log.info("Received new POST request")

        def message = "Entity successfully created"
        def body    = new HashMap<>()

        body.put(MESSAGE, message)
        body.put(ENTITY,  service.save(entity))

        log.info(message)

        return new ResponseEntity(body, HttpStatus.ACCEPTED)
    }

    @PutMapping("/{id}")
    def update(@PathVariable ID id, @RequestBody T entity) {

        log.info("Received new PUT request")

        def message = "Entity successfully updated"
        def body    = new HashMap<>()

        body.put(MESSAGE, message)
        body.put(ENTITY,  service.update(entity))

        log.info(message)

        return new ResponseEntity(body, HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/{id}")
    def deleteById(@PathVariable ID id) {

        log.info("Received new DELETE request")

        def body    = new HashMap<>()
        def entity  = service.deleteById(id)
        def message = "Entity successfully deleted"

        body.put(MESSAGE, message)
        body.put(ENTITY,  entity)

        log.info(message)

        return new ResponseEntity(body, HttpStatus.ACCEPTED)
    }

}
