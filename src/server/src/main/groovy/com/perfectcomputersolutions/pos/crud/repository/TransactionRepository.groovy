package com.perfectcomputersolutions.pos.crud.repository

import com.perfectcomputersolutions.pos.model.Transaction
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * JPA repository that handles database queries related to transactions.
 */
interface TransactionRepository extends MongoRepository<Transaction, String> {

}
