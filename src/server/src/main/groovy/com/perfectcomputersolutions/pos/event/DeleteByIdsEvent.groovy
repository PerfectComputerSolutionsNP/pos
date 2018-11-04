package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity

import java.lang.reflect.Type

class DeleteByIdsEvent<T extends ModelEntity> extends GenericEvent<T> {

    DeleteByIdsEvent(Type type) {
        super(type)
    }
}
