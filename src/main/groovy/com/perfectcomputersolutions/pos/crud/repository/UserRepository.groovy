package com.perfectcomputersolutions.pos.crud.repository

import com.perfectcomputersolutions.pos.model.User
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * JPA repository that handles database queries related to users.
 */
interface UserRepository extends MongoRepository<User, Long> {

}