package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.model.Product
import com.perfectcomputersolutions.pos.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService extends CrudService<Product, Long> {

    @Autowired ProductRepository repository
}
