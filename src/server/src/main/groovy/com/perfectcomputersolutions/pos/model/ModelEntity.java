package com.perfectcomputersolutions.pos.model;

import com.perfectcomputersolutions.pos.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
* Superclass for all database entities. This class contains base
* methods that all model entities include such as equality checks,
* hashing and serialization.
*/
@MappedSuperclass
public class ModelEntity {

    private static final Logger log = LoggerFactory.getLogger(ModelEntity.class);

    /**
    * Unique entity id number for use with database.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public boolean equals(Object obj) {

        // TODO - Make sure we compare by reference not implicit call to equals() via Groovy, otherwise infinite recursion

        // Compare by reference
        if (this == obj)
            return true;

        // Check that the class types are equal via reflection
        if (!this.getClass().equals(obj.getClass()))
            return false;

        try {

            ModelEntity entity = (ModelEntity) obj;

            return this.id.equals(entity.id);

        } catch (ClassCastException e) {

            String msg = "This object and supplied object are both of type " + this.getClass() + " " +
                      "a ClassCastException was still thrown. Please report this error to administrator.";

            log.error(msg);
            log.error(e.toString());

            return false;
        }

    }

    /**
    * Unique hash id for this entity.
    *
    * @return Hash code for this entity.
    */
    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    /**
    * Returns a JSON string representation of this entity..
    *
    * @return JSON string representing this entity.
    */
    @Override
    public String toString() {

        return Utility.serialize(this);
    }

}
