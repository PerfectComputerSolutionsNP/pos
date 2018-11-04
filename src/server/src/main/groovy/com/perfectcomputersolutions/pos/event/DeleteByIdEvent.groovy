package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity

import java.lang.reflect.Type

class DeleteByIdEvent<T extends ModelEntity> extends GenericEvent<T> {

    T entity

    DeleteByIdEvent(T entity, Type type) {
        super(type)

        if (entity == null)
            throw new IllegalAccessException("")

        this.entity = entity
    }
}
