package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.ModelEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface ModelEntityRepository<T extends ModelEntity, ID extends Serializable> extends CrudRepository<T, ID> {

    // TODO - Implement utility method for bulk deletion by List<Long> id
}
