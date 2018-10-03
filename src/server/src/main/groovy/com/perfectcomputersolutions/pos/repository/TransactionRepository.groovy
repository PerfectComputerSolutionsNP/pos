package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
