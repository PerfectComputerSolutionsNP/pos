package com.perfectcomputersolutions.pos.crud.controller

import com.perfectcomputersolutions.pos.crud.service.ProductService
import com.perfectcomputersolutions.pos.model.Product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller for API calls related to products.
 *
 * @see Product
 */
@RestController
@RequestMapping("/product")
class ProductController extends CrudController<Product, String> {

//    private static final Logger Log = LoggerFactory.getLogger(ProductController.class)

    @Autowired ProductService service
}
