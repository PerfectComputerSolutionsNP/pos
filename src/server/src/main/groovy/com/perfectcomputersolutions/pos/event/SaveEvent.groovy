package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity

import java.lang.reflect.Type

class SaveEvent<T extends ModelEntity> extends GenericEvent<T> {

    final T input
    final T output

    SaveEvent(T input, T output, Type type) {

        super(type)

        this.input  = Objects.requireNonNull(input, "Input must not be null")
        this.output = Objects.requireNonNull(output, "Output bust not be null")
    }
}
