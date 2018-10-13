package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.ModelEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository

import java.awt.print.Pageable

@NoRepositoryBean
interface ModelEntityRepository<T extends ModelEntity, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    // TODO - Implement utility method for bulk deletion by List<Long> id

    // https://www.youtube.com/watch?v=vVfJ2lBCZ60
    // https://www.baeldung.com/rest-api-pagination-in-spring
    // https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
}
