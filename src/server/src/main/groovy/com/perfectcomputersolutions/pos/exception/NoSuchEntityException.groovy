package com.perfectcomputersolutions.pos.exception

import com.perfectcomputersolutions.pos.model.ModelEntity

class NoSuchEntityException<T extends ModelEntity, ID extends Serializable> extends CrudException {

    NoSuchEntityException(String msg) {

        super(msg)
    }

    NoSuchEntityException(ID id) {

        super(id == null ? "Null id" : "No entity for id: " + id)
    }
}
