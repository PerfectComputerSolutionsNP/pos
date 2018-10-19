package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.Authority
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long> {
}
