package com.perfectcomputersolutions.pos.crud.repository

import com.perfectcomputersolutions.pos.model.Product
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * JPA repository that handles database queries related to products.
 */
interface ProductRepository extends MongoRepository<Product, Long> {

}
