package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity

import java.lang.reflect.Type

class FindByIdEvent<T extends ModelEntity> extends GenericEvent<T> {

    final Serializable id
    final T            entity

    FindByIdEvent(Serializable id, T entity, Type type) {
        super(type)

        this.id     = Objects.requireNonNull(id, "Id must not be null")
        this.entity = Objects.requireNonNull(entity, "Entity must not be null")
    }
}
