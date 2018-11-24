package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.factory.NotificationFactory
import com.perfectcomputersolutions.pos.model.User
import com.perfectcomputersolutions.pos.repository.UserRepository
import com.perfectcomputersolutions.pos.utility.Violation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import java.sql.Timestamp

@Service
class UserService extends CrudService<User, Long> {

    private static final Logger log = LoggerFactory.getLogger(UserService.class)

    @Autowired EmailService        emailService
    @Autowired NotificationFactory notificationFactory
    @Autowired UserRepository      repository
    @Autowired PasswordEncoder     encoder

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

    User findByUsername(String username) {

        log.info("Retrieving user by username: '${username}'")

        if (!existsByUsername(username))
            throw new NoSuchEntityException("No user exists for username: ${username}")

        return repository.findByUsername(username)
    }

    @Override
    User update(Long id, User user) {

        def original = findById(id)
        def password = encoder.encode(user.password)

        if (password != original.password) {

            user.lastPasswordResetDate = new Timestamp(System.currentTimeMillis())
            user.password              = password
        }

        return (User) super.update(id, user)
    }


    @Override
    User save(User user) {

        user.password = encoder.encode(user.password)

        if (repository.existsByUsername(user.username)) {

            throw new ValidationException(new Violation(
                    "username",
                    "A user already exists with username: ${user.username}",
                    user.class.simpleName
            ))
        }

        if (repository.existsByEmail(user.email)) {

            throw new ValidationException(new Violation(
                    "email",
                    "A user already exists with email: ${user.email}",
                    user.class.simpleName
            ))
        }

        return (User) super.save(user)
    }
}
