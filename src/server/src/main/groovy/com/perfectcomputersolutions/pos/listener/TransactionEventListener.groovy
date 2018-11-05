package com.perfectcomputersolutions.pos.listener

import com.perfectcomputersolutions.pos.annotation.AsyncEventListener
import com.perfectcomputersolutions.pos.event.SaveEvent
import com.perfectcomputersolutions.pos.factory.NotificationFactory
import com.perfectcomputersolutions.pos.model.Transaction
import com.perfectcomputersolutions.pos.service.EmailService
import com.perfectcomputersolutions.pos.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TransactionEventListener {

    @Autowired EmailService        emailService
    @Autowired NotificationFactory notificationFactory
    @Autowired TransactionService  transactionService

    @AsyncEventListener
    def save(SaveEvent<Transaction> event) {

        def transaction = transactionService.findById(event.output.id)
        def customer    = transaction.customer

        if (customer && transaction.notifyCustomer) {

            def variables = [
                    "customer" : customer,
                    "date"     : transaction.createdDate,
                    "dollars"  : transaction.dollars,
                    "products" : transaction.products,
                    "services" : transaction.services
            ]

            def email = notificationFactory.getNotification(
                    transaction.customer.email,
                    "Your receipt from POS",
                    "email/transaction",
                    variables
            )

            emailService.deliver(email)
        }
    }
}
