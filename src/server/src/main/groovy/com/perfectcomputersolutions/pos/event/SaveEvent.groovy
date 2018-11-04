package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity

import java.lang.reflect.Type

class SaveEvent<T extends ModelEntity> extends GenericEvent<T> {

    T entity

    SaveEvent(T entity, Type type) {

        super(type)

        if (entity == null)
            throw new IllegalArgumentException("Entity argument must not be null")

        this.entity = entity
    }
}
