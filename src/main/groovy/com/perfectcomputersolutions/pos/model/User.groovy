package com.perfectcomputersolutions.pos.model

import com.perfectcomputersolutions.pos.util.ModelEntity
import org.springframework.data.mongodb.core.mapping.Document

/**
 * ModelEntity that represents a user of the POS system.
 */
@Document
class User extends ModelEntity {

    /**
     * First name.
     */
    private String firstName

    /**
     * Last name.
     */
    private String lastName

    /**
     * Update the user's first name.
     *
     * @param firstName New first name value.
     */
    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    /**
     * Update the user's last name.
     *
     * @param lastName New lat name value.
     */
    void setLastName(String lastName) {
        this.lastName = lastName
    }

    /**
     * Returns the user's first name.
     *
     * @return First name.
     */
    String getFirstName() {
        return this.firstName
    }

    /**
     * Return the user's last name.
     *
     * @return Last name.
     */
    String getLastName() {
        return this.lastName
    }
}