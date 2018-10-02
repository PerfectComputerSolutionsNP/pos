package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.EntityBatch
import com.perfectcomputersolutions.pos.model.ModelEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

abstract class UnprivilegedCrudController<T extends ModelEntity, ID extends Serializable> extends CrudController<T, ID> {

    // TODO - Implement batch upload, and batch delete by id

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
