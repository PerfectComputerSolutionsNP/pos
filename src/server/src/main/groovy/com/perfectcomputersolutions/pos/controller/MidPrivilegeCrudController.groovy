package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.ModelEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

abstract class MidPrivilegeCrudController<T extends ModelEntity, ID extends Serializable> extends UnprivilegedCrudController<T, ID> {

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    def deleteById(@PathVariable ID id) {
        return super.deleteById(id)
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    def save(@RequestBody T entity) {
        return super.save(entity)
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    def update(@PathVariable ID id, @RequestBody T entity) {
        return super.update(id, entity)
    }
}
