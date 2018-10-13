package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.EntityBatch
import com.perfectcomputersolutions.pos.model.ModelEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

/**
 * The {@code UnprivilegedCrudController} exposes all CRUD operations (GET, POST, PUT, DELETE) without
 * the requirement of any particular user role. Having full CRUD access with rarely be used in the application.
 * However, transaction management should be open to anymore. For example, a user should be able to process
 * a transaction (POST), lookup transaction information (GET), and modify or erase transactions if a mistake is
 * made (DELETE, PUT).
 *
 * @see ModelEntity
 *
 * @param <T> Generic type that extends ModelEntity
 * @param <ID> Generic type for {@code ModelEntity} objects (typically {@code Long)
 */
abstract class UnprivilegedCrudController<T extends ModelEntity, ID extends Serializable> extends CrudController<T, ID> {

    // TODO - Implement batch upload, and batch delete by id

    // Perhaps implement batch delete as a /delete path and POST with list in @RequestBody

    @GetMapping
    def findAll(
            @RequestParam int               page,
            @RequestParam int               size,
            @RequestParam Optional<Boolean> sorted,
            @RequestParam Optional<String>  property
    ) {

        findAll(service, page, size, sorted, property)
    }

    @GetMapping("/{id}")
    def findById(@PathVariable ID id) {

        findById(id, service)
    }

    @PostMapping
    def save(@RequestBody T entity) {

        save(entity, service)
    }

    @PostMapping("/batch")
    def saveAll(@RequestBody EntityBatch<T> entities) {

        saveAll(entities, service)
    }

    @PutMapping("/{id}")
    def update(@PathVariable ID id, @RequestBody T entity) {

        update(id, entity, service)
    }

    @DeleteMapping("/{id}")
    def deleteById(@PathVariable ID id) {

        deleteById(id, service)
    }

}
