package com.perfectcomputersolutions.pos.utility

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Utility functions for reuse throughout the application.
 */
class Utility {

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
