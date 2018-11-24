package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.annotation.Legit
import com.perfectcomputersolutions.pos.annotation.NoNullArgs
import com.perfectcomputersolutions.pos.event.CustomerPickupEvent
import com.perfectcomputersolutions.pos.model.Transaction
import com.perfectcomputersolutions.pos.payload.Batch
import com.perfectcomputersolutions.pos.repository.TransactionRepository
import com.perfectcomputersolutions.pos.utility.EventPublisher
import com.perfectcomputersolutions.pos.utility.Utility
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

import java.sql.Timestamp

/**
 * Service responsible for managing the {@code Transaction} entity.
 *
 * @see Transaction
 */
@Legit
@NoNullArgs
@Service
class TransactionService extends CrudService<Transaction, Long> {

    // TODO - https://www.baeldung.com/spring-scheduled-tasks
    // Schedule checking DB for transactions with a pending pickup date / time
    // then publish event with list of transactions such that event listener
    // emails each user by transaction

    @Autowired TransactionRepository repository
    @Autowired EventPublisher        publisher

    @Override
    Transaction save(Transaction transaction) {

        def user = Utility.currentUserDetails.get()

        Utility.requireAllMatch("Transaction.user.id must match current user id", transaction.user.id, user.id)

        super.save(transaction)
    }
}
