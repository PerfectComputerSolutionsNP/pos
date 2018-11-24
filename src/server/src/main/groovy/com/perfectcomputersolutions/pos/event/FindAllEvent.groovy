package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity

import java.lang.reflect.Type

class FindAllEvent<T extends ModelEntity> extends GenericEvent<T> {

    Iterable<T> entities

    FindAllEvent(Iterable<T> entities, Type type) {
        super(type)

        if (entities == null)
            throw new IllegalArgumentException("Argument must not be null")

        this.entities = entities
    }
}
