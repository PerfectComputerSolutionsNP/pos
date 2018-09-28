package com.perfectcomputersolutions.pos.model

import org.springframework.data.mongodb.core.mapping.Document

/**
 * ModelEntity that represents a user of the POS system.
 */
@Document
class User extends ModelEntity {

    String firstName

    String lastName

    String username

    String password
}