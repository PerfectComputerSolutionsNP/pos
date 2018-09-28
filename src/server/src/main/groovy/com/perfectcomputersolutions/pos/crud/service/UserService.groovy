package com.perfectcomputersolutions.pos.crud.service

import com.perfectcomputersolutions.pos.crud.repository.UserRepository
import com.perfectcomputersolutions.pos.crud.validator.UserValidator
import com.perfectcomputersolutions.pos.model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * Service for managing users in database.
 *
 * @see User
 */
@Service
class UserService extends CrudService<User, String> implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class)

    @Autowired UserRepository repository
    @Autowired UserValidator  validator

    /**
     * Find and return the user by username.
     *
     * @param userName Username to search by.
     * @return User associated with the username.
     */
    User findByUsername(String userName) {

        log.info("Finding user with username: " + userName)

        def user = repository.findByUsername(userName)

        if (user == null)
            log.info("User not found for username: "+ userName)

        else
            log.info("Found user with username: " + userName)

        return user
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = findByUsername(username)

        if (user == null)
            throw new UsernameNotFoundException("User not found")

        def authorities = Arrays.asList(new SimpleGrantedAuthority("user"))

        return new org.springframework.security.core.userdetails.User(
                user.username,
                user.password,
                authorities
        )
    }

}

