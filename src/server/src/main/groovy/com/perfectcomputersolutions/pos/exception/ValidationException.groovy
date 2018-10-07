package com.perfectcomputersolutions.pos.exception

import com.perfectcomputersolutions.pos.model.ModelEntity

import javax.validation.ConstraintViolation

/**
 * Exception that is thrown when a validation error occurs
 * for a particular ModelEntity. This exception is typically thrown
 * as a result of a field being invalid when a POST request occurs.
 *
 * @see ModelEntity
 *
 * @param <T> Generic type of exception.
 */
class ValidationException<T> extends CrudException {

    /**
     * Set of violation objects that detail why
     * validation failed for a particular entity.
     */
    final violations = new HashSet<Violation>()

    /**
     * Constructs the exception from a set of {@code ConstraintViolation}
     * objects that will eventually be converted into simplified {@code Violation}
     * objects for use in HTTP responses from the exception handler.
     *
     * @see ConstraintViolation
     * @see Violation
     *
     * @param violations Set of {@code ConstraintViolation} objects to construct from.
     */
    ValidationException(Set<ConstraintViolation<T>> violations) {

        super("Validation failed")

        if (violations.size() == 0)
            throw new IllegalArgumentException("ViolationException thrown without any actual violations")

        for (def violation : violations) {

            def v = new Violation()

            v.message = violation.message
            v.field   = violation.propertyPath
            v.entity  = violation.rootBeanClass.simpleName

            this.violations.add(v)
        }
    }

    ValidationException(Violation violation) {

        if (violation == null)
            throw new IllegalArgumentException("Violation object is null")

        this.violations.add(violation)
    }
}
