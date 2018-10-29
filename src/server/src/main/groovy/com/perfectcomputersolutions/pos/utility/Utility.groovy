package com.perfectcomputersolutions.pos.utility

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.perfectcomputersolutions.pos.exception.CaughtException
import com.perfectcomputersolutions.pos.exception.ValidationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

import javax.servlet.http.HttpServletRequest
import javax.validation.Validation
import javax.validation.Validator

/**
 * Utility functions for reuse throughout the application.
 */
@Component
class Utility {

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

    static UserDetails getCurrentUserDetails() {

        def authentication = SecurityContextHolder.getContext()
                                                  .getAuthentication()

        if (authentication != null) {

            def principal = authentication.principal

            return principal instanceof UserDetails ? (UserDetails) principal : null
        }

        return null
    }
}
