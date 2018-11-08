package com.perfectcomputersolutions.pos.model;

import com.perfectcomputersolutions.pos.utility.Auditable;
import com.perfectcomputersolutions.pos.utility.Utility;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.AccessType.PROPERTY;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
* Superclass for all database entities. This class contains base
* methods that all model entities include such as equality checks,
* hashing and serialization.
*/
@MappedSuperclass
public class ModelEntity extends Auditable<String> {

    /**
    * Unique entity id number for use with database.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @ApiModelProperty(notes = "The database generated ID. This should only be specified when performing HTTP - UPDATE.")
    private Long id;

    /**
     * Returns the numeric id for this entity.
     *
     * @return This entity's id.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the numeric id for this entity.
     */
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

        // Considering that the id field must not be null, we must account
        // for both cases. Typically, entities with an initialized id field
        // are entities that have already been persisted and are now being
        // retrieved from the database via some sort GET request and entities
        // that do not have an id initialized are typically entities that are
        // being inserted via a POST request. That being said, we will not find
        // Collections of entities that have a mixture of both initialized and
        // uninitialized id fields. Therefore, our equality check goes as follows:
        //
        // 1. Check object reference. If they are equal, return true.
        // 2. Check type, if they are not the same, the objects cannot be true
        // 3. If the types are the same, check if both ids are null.
        //    3.1 If ids are null, perform a field-by-field equality check via reflection
        //    3.2 If ids are not null, check the equality of the ids
        //    3.3 If one id is null and the other is not, the objects cannot be equal

        if (this == obj)
            return true;

        if (!this.getClass().equals(obj.getClass()))
            return false;

        try {

            ModelEntity entity = (ModelEntity) obj;

            // Equal by field
            if (this.id == null && entity.id == null) {

                return EqualsBuilder.reflectionEquals(this, entity);

            // Equal by id
            } else if (this.id != null && entity.id != null) {

                return Objects.equals(this.id, entity.id);

            } else {
                return false;
            }

        } catch (ClassCastException e) {

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
