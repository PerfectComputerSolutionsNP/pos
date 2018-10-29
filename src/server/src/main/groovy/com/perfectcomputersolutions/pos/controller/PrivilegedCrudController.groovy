package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.payload.Batch
import com.perfectcomputersolutions.pos.model.ModelEntity
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
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

    @GetMapping("/count")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Count the number of entities associated with resource name. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def count() {

        count(service)
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Find all (paginated) entities by specifying page number, size. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
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
    @ApiOperation(value = "Find an entity by it's id. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def findById(@PathVariable ID id) {

        findById(id, service)
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Persist an entity to storage. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def save(@RequestBody T entity) {

        save(entity, service)
    }

    @PostMapping("/batch")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Persist several entities to storage. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def saveAll(@RequestBody Batch<T> entities) {

        saveAll(entities, service)
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update an entity. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def update(@PathVariable ID id, @RequestBody T entity) {

        update(id, entity, service)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete an entity by it's id. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def deleteById(@PathVariable ID id) {

        deleteById(id, service)
    }

    @DeleteMapping("/batch")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete several entities by their ids. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def deleteByIds(@RequestBody Batch<ID> ids) {

        deleteByIds(ids, service)
    }
}
