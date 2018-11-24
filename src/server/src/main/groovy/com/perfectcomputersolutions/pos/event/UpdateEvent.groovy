package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.ModelEntity

import java.lang.reflect.Type

class UpdateEvent<T extends ModelEntity> extends GenericEvent<T> {

    final Serializable id
    final T            input
    final T            output

    UpdateEvent(Serializable id, T input, T output, Type type) {
        super(type)

        this.id     = Objects.requireNonNull(id, "Id must not be null")
        this.input  = Objects.requireNonNull(input, "Input entity must not be null")
        this.output = Objects.requireNonNull(output, "Output entity must not be null")
    }
}
