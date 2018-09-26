package com.perfectcomputersolutions.pos.crud.service

import com.perfectcomputersolutions.pos.crud.repository.UserRepository
import com.perfectcomputersolutions.pos.crud.validator.UserValidator
import com.perfectcomputersolutions.pos.model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.security.InvalidParameterException

/**
 * Service for managing users in database.
 *
 * @see User
 */
@Service
class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class)

    @Autowired
    UserRepository repository

    /**
     * Returns list of all users.
     *
     * @return List of all users.
     */
    List<User> findAll() {

        log.info("Finding all users")

        return repository.findAll()
    }

    /**
     * Creates and save a new user to database.
     *
     * @param user User to save.
     * @return Copy of the saved user object with it's user id.
     */
    def create(User user) {

        if (UserValidator.valid(user))
            return repository.save(user)

        else
            throw new InvalidParameterException("Invalid user")
    }
}

