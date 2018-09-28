package com.perfectcomputersolutions.pos.crud.validator

import com.perfectcomputersolutions.pos.crud.repository.UserRepository
import com.perfectcomputersolutions.pos.model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.security.InvalidParameterException

/**
 * This class contains static methods for validating a {@code User} object.
 *
 * @see User
 */
@Component
class UserValidator extends Validator<User> {

    private static final Logger log = LoggerFactory.getLogger(UserValidator.class)

    @Autowired
    UserRepository repository

    void valid(User user) {

        def message = null

        log.info("Validating user: " + user)

        if (user.username == null)
            throw new InvalidParameterException("")

        // Check if we already have a user under that username
        if (repository.findByUsername(user.username) != null)
            throw new InvalidParameterException("Username already present: " + user.username)

        // Check instance variable fields

        // USERNAME

        if (user.username == null)
            message = "Username must not be empty"

        else if (empty(user.username))
            message = "Username must not be empty"

        else if (!validLength(user.username))
            message = "Username must be between 1 and " + MAX_FIELD_LENGTH + " characters inclusive"

        // FIRST NAME

        // LAST NAME

        // ID

        else if (user.id != null)
            message = "The id field must be empty as it will be automatically assigned"

        // If any of the above conditions evaluated
        // to true, then the user object is not valid
        if (message != null)
            throw new InvalidParameterException(message)

        else
            log.info("User is valid")
    }
}