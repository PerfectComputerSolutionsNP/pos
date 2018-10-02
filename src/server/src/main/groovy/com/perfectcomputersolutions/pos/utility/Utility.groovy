package com.perfectcomputersolutions.pos.utility

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.perfectcomputersolutions.pos.exception.ValidationException

import javax.validation.Validation
import javax.validation.Validator

/**
 * Utility functions for reuse throughout the application.
 */
class Utility {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator()

    static <T> boolean valid(T obj) {

        def violations = validator.validate(obj)

        return violations.size() == 0
    }

    static <T> void validate(T obj) {

        def violations = validator.validate(obj)

        if (violations.size() != 0)
            throw new ValidationException<T>(violations)
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

            throw new RuntimeException("Could not serialize object", e)
        }
    }
}
