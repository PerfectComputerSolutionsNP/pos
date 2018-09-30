package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.model.Transaction
import com.perfectcomputersolutions.pos.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService extends CrudService<Transaction, Long> {

    @Autowired TransactionRepository repository
}
