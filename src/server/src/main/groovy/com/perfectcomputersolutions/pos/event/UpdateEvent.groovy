package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity

import java.lang.reflect.Type

class UpdateEvent<T extends ModelEntity> extends GenericEvent<T> {

    T entity

    UpdateEvent(T entity, Type type) {
        super(type)

        if (entity == null)
            throw new IllegalAccessException("Entity must not be null")

        this.entity = entity
    }
}
