package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity

import java.lang.reflect.Type

class SaveAllEvent<T extends ModelEntity> extends GenericEvent<T> {

    SaveAllEvent(Type type) {
        super(type)
    }
}
