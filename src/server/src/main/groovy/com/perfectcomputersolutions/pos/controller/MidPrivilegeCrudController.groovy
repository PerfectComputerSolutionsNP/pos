package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.utility.EntityBatch
import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.utility.IdBatch
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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
    def final save(@RequestBody T entity) {

        super.save(entity)
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    def final saveAll(@RequestBody EntityBatch<T> entities) {

        super.saveAll(entities)
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    def final update(@PathVariable ID id, @RequestBody T entity) {

        super.update(id, entity)
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    def final deleteById(@PathVariable ID id) {

        super.deleteById(id)
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    def final deleteByIds(@RequestBody IdBatch<ID> ids) {

        super.deleteByIds(ids)
    }
}
