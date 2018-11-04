package com.perfectcomputersolutions.pos.model;

import com.perfectcomputersolutions.pos.utility.Auditable;
import com.perfectcomputersolutions.pos.utility.Utility;
import io.swagger.annotations.ApiModelProperty;
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

        if (this == obj)
            return true;

        if (!this.getClass().equals(obj.getClass()))
            return false;

        try {

            ModelEntity entity = (ModelEntity) obj;

            return this.id.equals(entity.id);

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
