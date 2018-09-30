package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.repository.UserRepository
import com.perfectcomputersolutions.pos.exception.DuplicateEntityException
import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.exception.Violation
import com.perfectcomputersolutions.pos.model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Service for managing users in database.
 *
 * @see User
 */
//@Service
//class UserService extends CrudService<User, String> implements UserDetailsService {
//
//    private static final Logger log = LoggerFactory.getLogger(UserService.class)
//
//    @Autowired UserRepository  repository
//    @Autowired PasswordEncoder encoder
//
//    boolean existsByName(String username) {
//
//        if (username == null)
//            throw new NoSuchEntityException()
//
//        return repository.findByUsername(username) != null
//    }
//
//    @Override
//    User save(User user) {
//
//        validate(user)
//
//        if (existsByName(user.username)) {
//
//            def violation = new Violation()
//
//            violation.field   = "username"
//            violation.message = "A user already exist for username: " + user.username
//            violation.entity  = user.class.simpleName
//
//            throw new DuplicateEntityException(violation)
//        }
//
//        user.password = encoder.encode(user.password)
//
//        return (User) super.save(user)
//    }
//
//    @Override
//    User update(User user) {
//
//        // TODO - Be careful for password
//
//        return user
//    }
//
//    /**
//     * Find and return the user by username.
//     *
//     * @param userName Username to search by.
//     * @return User associated with the username.
//     */
//    User findByUsername(String userName) {
//
//        log.info("Finding user with username: " + userName)
//
//        def user = repository.findByUsername(userName)
//
//        if (user == null)
//            log.info("User not found for username: " + userName)
//
//        else
//            log.info("Found user with username: " + userName)
//
//        return user
//    }
//
//    @Override
//    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        log.info("Loading user by username: " + username)
//
//        def user = findByUsername(username)
//
//        if (user == null) {
//
//            log.info("Could not load user by username: " + username)
//
//            throw new UsernameNotFoundException("User not found")
//        }
//
//        def authorities = Arrays.asList(new SimpleGrantedAuthority("user"))
//
//        return new org.springframework.security.core.userdetails.User(
//                user.username,
//                user.password,
//                authorities
//        )
//    }
//
//}

