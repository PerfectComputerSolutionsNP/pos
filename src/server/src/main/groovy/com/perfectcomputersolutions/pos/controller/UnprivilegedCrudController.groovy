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

abstract class UnprivilegedCrudController<T extends ModelEntity, ID extends Serializable> implements CrudController<T, ID> {

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
