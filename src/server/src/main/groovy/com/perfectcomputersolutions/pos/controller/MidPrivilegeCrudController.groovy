package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.payload.Batch
import com.perfectcomputersolutions.pos.model.ModelEntity
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

/**
 * The {@code MidPrivilegeCrudController} is a combination of the {@code PrivilegedCrudController} and the
 * {@code UnprivilegedCrudController}. All read operations that are mapped to HTTP - GET do not require
 * any specific role. However, methods such as DELETE, POST, and PUT require the ADMIN role. This creates
 * creates a controller that has read-only rights for non-admins and read-write access for admins. This is
 * useful for controllers that manage inventory for example. All users should be able to see inventory,
 * but only administrators can modify the inventory system.
 *
 * @see ModelEntity
 *
 * @param <T> Generic type that extends ModelEntity
 * @param <ID> Generic type for {@code ModelEntity} objects (typically {@code Long)
 */
abstract class MidPrivilegeCrudController<T extends ModelEntity, ID extends Serializable> extends UnprivilegedCrudController<T, ID> {

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Persist an entity to storage. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def save(@RequestBody T entity) {

        super.save(entity)
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Persist several entities to storage. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def saveAll(@RequestBody Batch<T> entities) {

        super.saveAll(entities)
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update an entity. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def update(@PathVariable ID id, @RequestBody T entity) {

        super.update(id, entity)
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete an entity by id. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def deleteById(@PathVariable ID id) {

        super.deleteById(id)
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete several entities by their id. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def deleteByIds(@RequestBody Batch<ID> ids) {

        super.deleteByIds(ids)
    }
}
