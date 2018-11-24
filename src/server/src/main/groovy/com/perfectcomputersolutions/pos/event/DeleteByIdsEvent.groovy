package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.payload.Batch

import java.lang.reflect.Type

class DeleteByIdsEvent<T extends ModelEntity> extends GenericEvent<T> {

    Batch<Serializable> ids

    DeleteByIdsEvent(Batch<Serializable> ids, Type type) {

        super(type)

        this.ids = Objects.requireNonNull(ids, "Ids must not be null")
    }
}
