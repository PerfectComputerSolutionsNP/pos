package com.perfectcomputersolutions.pos.crud.service

import com.perfectcomputersolutions.pos.crud.repository.TransactionRepository
import com.perfectcomputersolutions.pos.crud.validator.TransactionValidator
import com.perfectcomputersolutions.pos.model.Transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.security.InvalidParameterException

/**
 * Service for managing transactions in database.
 */
@Service
class TransactionService {

    private static final Logger = LoggerFactory.getLogger(TransactionService.class)

    @Autowired
    TransactionRepository repository

    /**
     * Returns list of all transactions
     *
     * @return List of all transactions
     */

    List<Transaction> findAll() {

        Log.info("Finding all transactions")
    }

    /**
     * Creates and save a new transaction to database.
     *
     * @param transaction Transaction to save.
     * @return Copy of the saved transaction object with its transaction id.
     */
    def create(Transaction transaction) {

        if(TransactionValidator.valid(transaction))
            return repository.save(transaction)

        else
            throw new InvalidParameterException("Invalid transaction")
    }
}
