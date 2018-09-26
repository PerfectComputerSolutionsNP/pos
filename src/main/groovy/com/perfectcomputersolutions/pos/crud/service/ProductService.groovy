package com.perfectcomputersolutions.pos.crud.service

import com.perfectcomputersolutions.pos.crud.repository.ProductRepository
import com.perfectcomputersolutions.pos.crud.validator.ProductValidator
import com.perfectcomputersolutions.pos.model.Product
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.security.InvalidParameterException

/**
 * Service for managing products in database.
 *
 * @see Product
 */
@Service
class ProductService {

    private static final Logger Log = LoggerFactory.getLogger(ProductService.class)

    @Autowired
    ProductRepository repository

    /**
     * Returns list of all products.
     *
     * @return List of all products.
     */

    List<Product> findAll() {

        Log.info("Finding all products")

        return repository.findAll()
    }

    /**
     *
     * @param product Product to save.
     * @return Copy of the saved product object with its product id
     */

    def create(Product product) {

        if(ProductValidator.valid(product))
            return repository.save(product)
        else
            throw new InvalidParameterException("Invalid product")
    }
}
