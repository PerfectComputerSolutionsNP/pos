package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.NamedEntity
import com.perfectcomputersolutions.pos.service.NamedEntityService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

abstract class InventoryController<T extends NamedEntity, ID extends Serializable> extends MidPrivilegeCrudController<T, ID> {

    abstract NamedEntityService getService()

    @GetMapping("/exists")
    @ApiOperation(value = "Determine if an entity exists by name. This operation does not requires any role.")
    def final existsByName(@RequestParam String name) {

        existsByName(name, service)
    }

    @GetMapping("/name")
    @ApiOperation(value = "Find an entity by it's name. This operation does not requires any role.")
    def final findByName(@RequestParam String name) {

        findByName(name, service)
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search for entities that contain a give name. This operation does not requires any role.")
    def final search(
            @RequestParam String            name,
            @RequestParam int               page,
            @RequestParam int               size,
            @RequestParam Optional<Boolean> sorted,
            @RequestParam Optional<String>  property
    ) {

        findByNameContaining(name, page, size, sorted, property, service)
    }

    static <E extends NamedEntity, I extends Serializable> ResponseEntity existsByName(
            String                   name,
            NamedEntityService<E, I> service) {

        def exists  = service.existsByName(name)
        def message = exists ?
                "An entity does exist with the name: "      + name :
                "An entity does not exist with the name: " + name

        def body = [

                (MESSAGE) : message,
                (EXISTS)  : exists
        ]

        respond(body, HttpStatus.OK)
    }

    static <E extends NamedEntity, I extends Serializable> ResponseEntity findByName(
            String                   name,
            NamedEntityService<E, I> service) {

        def body = [

                (MESSAGE) : RETRIEVED,
                (ENTITY)  : service.findByName(name)
        ]

        respond(body, HttpStatus.OK)
    }

    static <E extends NamedEntity, I extends Serializable> ResponseEntity findByNameContaining(
            String                   name,
            int                      page,
            int                      size,
            Optional<Boolean>        sorted,
            Optional<String>         property,
            NamedEntityService<E, I> service) {

        def body = service.findByNameContaining(name, page, size, sorted, property)

        respond(body, HttpStatus.OK)
    }
}
