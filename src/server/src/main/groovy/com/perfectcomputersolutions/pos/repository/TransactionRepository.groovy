package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.Transaction
import org.springframework.stereotype.Repository

import java.sql.Timestamp

@Repository
interface TransactionRepository extends ModelEntityRepository<Transaction, Long> {
}
