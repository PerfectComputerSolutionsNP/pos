package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity

import java.lang.reflect.Type

class DeleteByIdEvent<T extends ModelEntity> extends GenericEvent<T> {

    Serializable id
    T            entity

    DeleteByIdEvent(Serializable id, T entity, Type type) {
        super(type)

        this.id     = Objects.requireNonNull(id, "Id must not be null")
        this.entity = Objects.requireNonNull(entity, "Entity must not be null")
    }
}
