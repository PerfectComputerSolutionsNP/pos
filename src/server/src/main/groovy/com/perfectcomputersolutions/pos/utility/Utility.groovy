package com.perfectcomputersolutions.pos.utility

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.collect.ImmutableList
import com.perfectcomputersolutions.pos.exception.CaughtException
import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.security.JwtUser
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

import javax.validation.Validation
import javax.validation.Validator

/**
 * Utility functions for reuse throughout the application.
 */
@Component
class Utility {

    // https://stackoverflow.com/questions/22678891/how-to-get-user-id-from-customuser-on-spring-security

    private static final Logger log          = LoggerFactory.getLogger(Utility.class)
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator()

    static Set<Violation> violations(Object entity) {

        def violations = validator.validate(entity)
        def set        = new HashSet<Violation>()

        violations.forEach({v ->

            set.add(new Violation(v))
        })

        return set
    }

    static void validate(Object entity) {

        def violations = violations(entity)

        if (violations.size() != 0)
            throw new ValidationException(violations)
    }

    static void validate(Iterable<?> objects) {

        // TODO - Validate the object at object level?

        def map = new HashMap<Integer, Set<Violation>>()

        Set<Violation> violations

        int size = objects.size()

        for (int i = 0; i < size; i++) {

            violations = this.violations(objects[i])

            if (violations.size() != 0)
                map.put(i, violations)
        }

        if (!map.isEmpty())
            throw new ValidationException(new ViolationBatch(map))
    }

    /**
     * Serializes an object into JSON.
     *
     * @param obj Object to serialize.
     * @return JSON representation of the object.
     */
    static String serialize(Object obj) {

        try {

            return new ObjectMapper().writeValueAsString(obj)

        } catch (JsonProcessingException e) {

            throw new CaughtException("Could not serialize object", e)
        }
    }

    static Optional <JwtUser> getCurrentUserDetails() {

        def principal      = null
        def authentication = SecurityContextHolder.context
                                                  .authentication

        if (authentication != null) {

            principal = authentication.principal
            principal = principal instanceof UserDetails ? (JwtUser) principal : null

        }

        return Optional.of(principal)
    }

    static Optional<String> getCurrentUsername() {

        def details  = currentUserDetails
        def username = details.present ? details.get().username : null

        return Optional.of(username)
    }

    static void requireNotNull(String msg, Object... objects) {

        for (Object object : objects) {

            if (object == null)
                throw new IllegalArgumentException(msg)
        }

    }

    static void requireAllMatch(String msg, Object... objects) {

        def set = new HashSet<>()

        for (Object object : objects)
            set.add(object)

        log.info(set.toString())

        if (set.size() > 1)
            throw new IllegalArgumentException(msg)
    }
}
