package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.ModelEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

abstract class PrivilegedCrudController<T extends ModelEntity, ID extends Serializable> implements CrudController<T, ID> {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    def findAll() {

        findAll(service)
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
