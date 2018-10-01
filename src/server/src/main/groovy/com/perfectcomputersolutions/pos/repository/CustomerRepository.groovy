package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository extends CrudRepository<Customer, Long> {
}
