package com.perfectcomputersolutions.pos.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.annotation.Id

/**
* Superclass for all database entities. This class contains base
* methods that all model entities include such as equality checks,
* hashing and serialization.
*/
class ModelEntity {

    /**
    * Unique entity id number for use with database.
    */
    @Id
    public String id

    /**
    * Checks the equality of this object and a specified object. The
    * two entities are considered equal if they are of the exact same
    * type and their id's are equal.
    *
    * @param obj Specified object to compare to this entity.
    * @return Boolean flag indicating whether or not this entity and
    * the specified object are equal by type and id.
    */
    @Override
    boolean equals(Object obj) {

        // TODO - Make sure we compare by reference not implicit call to equals() via Groovy, otherwise infinite recursion

        // Compare by reference
        if (this == obj)
            return true

        // Check that the class types are equal via reflection
        if (!(obj instanceof ModelEntity))
            return false

        try {

            // Cast to ModelEntity
            ModelEntity entity = (ModelEntity) obj

            // Compare by id
            return this.id == entity.id

        } catch (ClassCastException e) {

            return false
        }

    }

    /**
    * Unique hash id for this entity.
    *
    * @return Hash code for this entity.
    */
    @Override
    int hashCode() {

        return Objects.hash(id)
    }

    /**
    * Returns a JSON string representation of this entity..
    *
    * @return JSON string representing this entity.
    */
    @Override
    String toString() {

        try {

            return new ObjectMapper().writeValueAsString(this)

        } catch (JsonProcessingException e) {

            throw new RuntimeException(e)
        }
    }

    /**
    * Returns a map representing this entity.
    *
    * @return Map representation of this entity.
    */
    @SuppressWarnings("unchecked")
    Map<String, Object> toMap() {

        return (Map<String, Object>) new ObjectMapper().convertValue(this, Map.class)
    }
}
