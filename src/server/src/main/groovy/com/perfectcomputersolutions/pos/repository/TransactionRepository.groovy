package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.Transaction
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
}
