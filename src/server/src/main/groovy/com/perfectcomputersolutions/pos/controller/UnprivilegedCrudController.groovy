package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.payload.EntityBatch
import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.payload.IdBatch
import io.swagger.annotations.ApiOperation
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

    @GetMapping("/count")
    @ApiOperation(value = "Count the number of entities associated with resource name. This operation does not requires any role.")
    def count() {

        count(service)
    }

    @GetMapping
    @ApiOperation(value = "Find all (paginated) entities by specifying page number, size. This operation does not requires any role.")
    def findAll(
            @RequestParam int               page,
            @RequestParam int               size,
            @RequestParam Optional<Boolean> sorted,
            @RequestParam Optional<String>  property
    ) {

        findAll(service, page, size, sorted, property)
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find an entity by it's id. This operation does not requires any role.")
    def findById(@PathVariable ID id) {

        findById(id, service)
    }

    @PostMapping
    @ApiOperation(value = "Persist an entity to storage. This operation does not requires any role.")
    def save(@RequestBody T entity) {

        save(entity, service)
    }

    @PostMapping("/batch")
    @ApiOperation(value = "Persist several entities to storage. This operation does not requires any role.")
    def saveAll(@RequestBody EntityBatch<T> entities) {

        saveAll(entities, service)
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an entity. This operation does not requires any role.")
    def update(@PathVariable ID id, @RequestBody T entity) {

        update(id, entity, service)
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an entity by it's id. This operation does not requires any role.")
    def deleteById(@PathVariable ID id) {

        deleteById(id, service)
    }

    @DeleteMapping("/batch")
    @ApiOperation(value = "Delete several entities by their ids. This operation does not requires any role.")
    def deleteByIds(@RequestBody IdBatch<ID> ids) {

        deleteByIds(ids, service)
    }
}
