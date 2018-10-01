package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.NamedEntity
import com.perfectcomputersolutions.pos.service.NamedEntityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

abstract class InventoryCrudController<T extends NamedEntity, ID extends Serializable> extends MidPrivilegeCrudController<T, ID> {

    abstract NamedEntityService getService()

    @GetMapping("/name")
    def findByName(@RequestParam(required = true) String name) {

        return findByName(name, service)
    }

    @GetMapping("/search")
    def search(@RequestParam(required = true) String name) {

        return findByNameContaining(name, service)
    }
}
