package com.perfectcomputersolutions.pos.utility

import org.apache.commons.lang3.StringUtils

import javax.validation.ConstraintViolation

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

    String field
    String message
    String entity

    Violation(ConstraintViolation<?> violation) {

        if (violation == null)
            throw new IllegalArgumentException("ConstraintViolation argument must not be null")

        this.field   = violation.propertyPath.toString()
        this.message = violation.message
        this.entity  = violation.rootBeanClass.simpleName
    }

    Violation (
            String field,
            String message,
            String entity) {

        if (
            StringUtils.isBlank(field)   ||
            StringUtils.isBlank(message) ||
            StringUtils.isBlank(entity)) {

            throw new IllegalArgumentException("All fields must not be null or empty")
        }

        this.field   = field
        this.message = message
        this.entity  = entity
    }

    @Override
    boolean equals(Object obj) {

        if (this == obj)
            return true

        else if (!(obj instanceof Violation))
            return false

        try {

            def violation = (Violation) obj

            return field    == violation.field   &&
                    message == violation.message &&
                    entity  == violation.entity

        } catch(ClassCastException ex) {

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
