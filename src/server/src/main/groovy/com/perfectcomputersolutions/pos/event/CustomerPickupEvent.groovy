package com.perfectcomputersolutions.pos.event

import com.perfectcomputersolutions.pos.model.Transaction
import com.perfectcomputersolutions.pos.payload.Batch
import com.perfectcomputersolutions.pos.utility.Utility

class CustomerPickupEvent {

    final Iterable<Transaction> transactions

    CustomerPickupEvent(Iterable<Transaction> transactions) {

        Utility.requireNotNull("Transaction batch must not be null", transactions)

        this.transactions = transactions
    }
}
