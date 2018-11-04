package com.perfectcomputersolutions.pos.listener

import com.perfectcomputersolutions.pos.annotation.AsyncEventListener
import com.perfectcomputersolutions.pos.event.SaveEvent
import com.perfectcomputersolutions.pos.factory.NotificationFactory
import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.model.User
import com.perfectcomputersolutions.pos.service.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserEventListener<T extends ModelEntity> {

    @Autowired EmailService        emailService
    @Autowired NotificationFactory notificationFactory

    @AsyncEventListener
    def save(SaveEvent<User> event) {

        def user    = event.entity
        def subject = "Registration confirmation"
        def email   = notificationFactory.getEmail(user, user.email, subject, "email/user-registered")

        emailService.deliver(email)
    }
}
