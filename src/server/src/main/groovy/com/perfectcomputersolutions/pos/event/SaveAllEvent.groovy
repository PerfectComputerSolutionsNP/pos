package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.payload.Batch

import java.lang.reflect.Type

class SaveAllEvent<T extends ModelEntity> extends GenericEvent<T> {

    final Batch<T> entities

    SaveAllEvent(Batch<T> entities, Type type) {

        super(type)

        this.entities = Objects.requireNonNull(entities, "Entity batch must not be null")
    }
}
