package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.model.User
import com.perfectcomputersolutions.pos.notifier.TemplateEmail
import com.perfectcomputersolutions.pos.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService extends CrudService<User, Long> {

    @Autowired UserRepository repository

    @Override
    User save(User entity) {

        def user    = (User) super.save(entity)
        def subject = "Registration confirmation"
        def email   = new TemplateEmail(user, user.email, subject, "email/user-registered")

        emailer.send(email)

        return user
    }
}