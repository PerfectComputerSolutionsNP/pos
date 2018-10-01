package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.model.Product
import com.perfectcomputersolutions.pos.repository.NamedEntityRepository
import com.perfectcomputersolutions.pos.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService extends NamedEntityService<Product, Long> {

    @Autowired private ProductRepository repository

    NamedEntityRepository<Product, Long> getRepository() {

        return this.repository
    }
}
