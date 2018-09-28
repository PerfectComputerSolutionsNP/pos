package com.perfectcomputersolutions.pos.crud.service

import com.perfectcomputersolutions.pos.crud.repository.ProductRepository
import com.perfectcomputersolutions.pos.crud.validator.ProductValidator
import com.perfectcomputersolutions.pos.model.Product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service for managing products in database.
 *
 * @see Product
 */
@Service
class ProductService extends CrudService<Product, String> {

//    private static final Logger Log = LoggerFactory.getLogger(ProductService.class)

    @Autowired ProductRepository repository
    @Autowired ProductValidator  validator
}
