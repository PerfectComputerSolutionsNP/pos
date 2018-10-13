package com.perfectcomputersolutions.pos.exception

import com.perfectcomputersolutions.pos.model.ModelEntity

final class NoSuchEntityException<T extends ModelEntity, ID extends Serializable> extends ThrownException {

    NoSuchEntityException(String msg) {

        super(msg)
    }

    NoSuchEntityException(ID id) {

        super("No entity for id: " + id)
    }
}
