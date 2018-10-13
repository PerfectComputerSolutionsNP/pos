package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.NamedEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface NamedEntityRepository<T extends NamedEntity, ID extends Serializable> extends ModelEntityRepository<T, ID> {

    T findByName(String name)

    Page<T> findByNameContaining(String name, Pageable pageable)
}
