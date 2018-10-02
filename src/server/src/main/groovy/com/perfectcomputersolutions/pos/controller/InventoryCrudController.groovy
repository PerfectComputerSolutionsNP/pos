package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.NamedEntity
import com.perfectcomputersolutions.pos.service.NamedEntityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * The {@code InventoryCrudController} is a {@code MidPrivilegeCrudController} that is used for entities related
 * to inventory. It is read-only for non-administrators and read-write for administrators. In addition to basic
 * CRUD operations, this class exposes a search functionality that allows the client to search for a product by name.
 * The two endpoints are {@code /name} and {@code /search}. Both endpoints require a singled request parameter
 * called {@code name}. The difference between the two is that {@code /name} will return a single entity if there
 * exists one for the exact name that was provided. The {@code /search} acts more like a google search in that it
 * will return a set of entities that have any similarity with the provided name. This can be used to create a dynamic
 * search bar the makes subsequent calls and returns results that are more refined.
 *
 * @see MidPrivilegeCrudController
 * @see com.perfectcomputersolutions.pos.model.ModelEntity
 *
 * @param <T> Generic type that extends ModelEntity
 * @param <ID> Generic type for {@code ModelEntity} objects (typically {@code Long)
 */
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
