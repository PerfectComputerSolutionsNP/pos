package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.model.User
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.transaction.annotation.Transactional

import java.awt.print.Pageable

@NoRepositoryBean
interface ModelEntityRepository<T extends ModelEntity, ID extends Serializable>
        extends PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<User> {


    // https://www.youtube.com/watch?v=vVfJ2lBCZ60
    // https://www.baeldung.com/rest-api-pagination-in-spring
    // https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
    // https://stackoverflow.com/questions/36763440/how-do-you-handle-with-bulk-deleting-by-an-array-of-ids-in-spring-data-jpa

    @Transactional
    void deleteByIdIn(Set<ID> ids)
}
