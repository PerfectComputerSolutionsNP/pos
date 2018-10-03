package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.NamedEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface NamedEntityRepository<T extends NamedEntity, ID extends Serializable> extends CrudRepository<T, ID> {

    T findByName(String name)

    Iterable<T> findByNameContaining(String name)
}
