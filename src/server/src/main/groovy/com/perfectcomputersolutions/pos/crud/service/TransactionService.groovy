package com.perfectcomputersolutions.pos.crud.service

import com.perfectcomputersolutions.pos.crud.repository.TransactionRepository
import com.perfectcomputersolutions.pos.crud.validator.TransactionValidator
import com.perfectcomputersolutions.pos.model.Transaction

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service for managing transactions in database.
 *
 * @see Transaction
 */
@Service
class TransactionService extends CrudService<Transaction, String> {

//    private static final Logger log = LoggerFactory.getLogger(TransactionService.class)

    @Autowired TransactionRepository repository
    @Autowired TransactionValidator  validator
}
