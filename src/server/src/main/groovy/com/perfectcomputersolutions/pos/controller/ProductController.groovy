package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.Product
import com.perfectcomputersolutions.pos.service.NamedEntityService
import com.perfectcomputersolutions.pos.service.ProductService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("product")
@Api(value="category",
        description='Operations pertaining to products. This controller requires ADMIN rights to edit inventory but not rights to view it.')
class ProductController extends InventoryController<Product, Long> {

    @Autowired ProductService service

    NamedEntityService<Product, Long> getService() {
        return this.service
    }
}
