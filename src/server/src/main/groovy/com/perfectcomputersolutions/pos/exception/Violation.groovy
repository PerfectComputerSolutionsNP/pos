package com.perfectcomputersolutions.pos.exception

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.perfectcomputersolutions.pos.utility.Utility
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Auxiliary object for use with returning user-friendly
 * and useful error messages in the HTTP response. The
 * Violation is a simplified version of {@code ConstraintViolation}
 * that does does expose internal workings of API which still
 * maintaining it's usefulness.
 *
 * @see javax.validation.ConstraintViolation
 */
class Violation {

    private static final Logger log = LoggerFactory.getLogger(Violation.class)

    String field
    String message
    String entity

    @Override
    boolean equals(Object obj) {

        if (this == obj)
            return true

        else if (!(obj instanceof  Violation))
            return false

        try {

            def violation = (Violation) obj

            return field   == violation.field   &&
                    message == violation.message &&
                    entity  == violation.entity

        } catch(ClassCastException ex) {

            log.error("Unexpected CastClassException", ex)

            return false
        }
    }

    @Override
    int hashCode() {

        return Objects.hash(field, message, entity)
    }

    @Override
    String toString() {

        Utility.serialize(this)
    }
}
