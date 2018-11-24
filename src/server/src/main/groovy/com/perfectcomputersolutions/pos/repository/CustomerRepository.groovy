package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository extends ModelEntityRepository<Customer, Long> {

 Page<Customer> findByFirstnameIgnoreCaseContainingOrLastnameIgnoreCaseContainingOrEmailIgnoreCaseContaining(String firstname, String lastname, String email, Pageable pageable)
}
