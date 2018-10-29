package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.factory.EmailFactory
import com.perfectcomputersolutions.pos.model.User
import com.perfectcomputersolutions.pos.repository.UserRepository
import com.perfectcomputersolutions.pos.utility.Violation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService extends CrudService<User, Long> {

    private static final Logger log = LoggerFactory.getLogger(UserService.class)

    @Autowired EmailService    emailSender
    @Autowired EmailFactory    emailFactory
    @Autowired UserRepository  repository
    @Autowired PasswordEncoder encoder

    boolean existsByUsername(String username) {

        log.info("Determining if user exists with username: ${username}")

        boolean exists = repository.existsByUsername(username)

        def msg = exists ?
                "A user does exist with the username: ${username}" :
                "A user does not exists with the username: ${username}"

        log.info(msg)

        return exists
    }

    boolean existsByEmail(String email) {

        log.info("Determining if user exists with email: ${email}")

        boolean exists = repository.existsByUsername(email)

        def msg = exists ?
                "A user does exist with the email: ${email}" :
                "A user does not exists with the email: ${email}"

        log.info(msg)

        return exists
    }

    @Override
    User save(User entity) {

        entity.password = encoder.encode(entity.password)

        if (repository.existsByUsername(entity.username)) {

            throw new ValidationException(new Violation(
                    "username",
                    "A user already exists with username: ${entity.username}",
                    entity.class.simpleName
            ))
        }

        if (repository.existsByEmail(entity.email)) {

            throw new ValidationException(new Violation(
                    "email",
                    "A user already exists with email: ${entity.email}",
                    entity.class.simpleName
            ))
        }

        def user    = (User) super.save(entity)
        def subject = "Registration confirmation"
        def email   = emailFactory.getEmail(user, user.email, subject, "email/user-registered")

        emailSender.deliver(email)

        return user
    }
}
