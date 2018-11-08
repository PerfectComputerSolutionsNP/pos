package com.perfectcomputersolutions.pos.listener

import com.perfectcomputersolutions.pos.annotation.AsyncEventListener
import com.perfectcomputersolutions.pos.event.SaveEvent
import com.perfectcomputersolutions.pos.factory.NotificationFactory
import com.perfectcomputersolutions.pos.model.Customer
import com.perfectcomputersolutions.pos.model.User
import com.perfectcomputersolutions.pos.service.EmailService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CustomerEventListener {

    @Autowired EmailService        emailService
    @Autowired NotificationFactory notificationFactory

    @AsyncEventListener
    def save(SaveEvent<Customer> event) {

        // TODO - Implement unsubscribe button @ bottom
        // https://stackoverflow.com/questions/853877/what-is-the-best-way-to-implement-an-unsubscribe-link-for-your-newsletter

        def customer = event.output
        def subject  = "Registration confirmation"
        def email    = notificationFactory.getEmail(customer, customer.email, subject, "email/customer-registered")

        emailService.deliver(email)
    }
}
