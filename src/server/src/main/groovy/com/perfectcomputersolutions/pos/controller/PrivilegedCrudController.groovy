package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.EntityBatch
import com.perfectcomputersolutions.pos.model.ModelEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

/**
 * The {@code PrivilegedCrudController} exposes CRUD operations with on an endpoint that requires the
 * ADMIN role. All operations exposed by this controller ADMIN rights. This is useful for user management.
 * For a Point of Sale system, an admin should authorize the creation and deletion of users and inventory.
 * These are good candidates for extending {@code PrivilegedCrudController} rather than a more open CRUD
 * controller such as {@code MidPrivilegeCrudController}.
 *
 * @see ModelEntity
 * @see MidPrivilegeCrudController
 *
 * @param <T> Generic type that extends ModelEntity
 * @param <ID> Generic type for {@code ModelEntity} objects (typically {@code Long)
 */
abstract class PrivilegedCrudController<T extends ModelEntity, ID extends Serializable> extends CrudController<T, ID> {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    def findAll(
            @RequestParam int               page,
            @RequestParam int               size,
            @RequestParam Optional<Boolean> sorted,
            @RequestParam Optional<String>  property
    ) {

        findAll(service, page, size, sorted, property)
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    def findById(@PathVariable ID id) {

        findById(id, service)
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    def save(@RequestBody T entity) {

        save(entity, service)
    }

    @PostMapping("/batch")
    @PreAuthorize("hasRole('ADMIN')")
    def saveAll(@RequestBody EntityBatch<T> entities) {

        saveAll(entities, service)
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    def update(@PathVariable ID id, @RequestBody T entity) {

        update(id, entity, service)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    def deleteById(@PathVariable ID id) {

        deleteById(id, service)
    }
}
