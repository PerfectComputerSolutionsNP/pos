package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.model.NamedEntity
import com.perfectcomputersolutions.pos.repository.NamedEntityRepository

abstract class NamedEntityService<T extends NamedEntity, ID extends Serializable> extends CrudService<T, ID> {

    abstract NamedEntityRepository<T, ID> getRepository()

    def findByName(String name) {

        findByName(name, repository)
    }

    def findByNameContaining(String name) {

        findByNameContaining(name, repository)
    }
}
