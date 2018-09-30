package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.Product
import com.perfectcomputersolutions.pos.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("persons")
class ProductController extends CrudController<Product, Long> {

    @Autowired ProductService service
}
