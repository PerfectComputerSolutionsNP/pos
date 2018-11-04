package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity

import java.lang.reflect.Type

class FindByIdEvent<T extends ModelEntity> extends GenericEvent<T> {

    T entity

    FindByIdEvent(T entity, Type type) {
        super(type)

        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null")

        this.entity = entity
    }
}
