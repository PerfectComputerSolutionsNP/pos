package com.perfectcomputersolutions.pos.exception

import com.perfectcomputersolutions.pos.model.ModelEntity

class DuplicateEntityException<T extends ModelEntity, ID extends Serializable> extends CrudException {

    DuplicateEntityException(Violation violation) {

        super("Duplicate entry")
    }

    DuplicateEntityException(ID id) {

        super("Duplicate entry for id: " + id)
    }
}
