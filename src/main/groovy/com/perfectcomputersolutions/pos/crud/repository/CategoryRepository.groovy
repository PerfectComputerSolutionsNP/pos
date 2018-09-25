package com.perfectcomputersolutions.pos.crud.repository

import com.perfectcomputersolutions.pos.model.Category
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * JPA repository that handles database queries related to categories.
 */
interface CategoryRepository extends MongoRepository<Category, Long> {

}