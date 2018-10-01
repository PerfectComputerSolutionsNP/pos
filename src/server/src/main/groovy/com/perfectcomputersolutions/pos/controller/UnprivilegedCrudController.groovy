package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.ModelEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

abstract class UnprivilegedCrudController<T extends ModelEntity, ID extends Serializable> extends CrudController<T, ID> {

    @GetMapping
    def findAll() {

        findAll(service)
    }

    @GetMapping("/{id}")
    def findById(@PathVariable ID id) {

        findById(id, service)
    }

    @PostMapping
    def save(@RequestBody T entity) {

        save(entity, service)
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
