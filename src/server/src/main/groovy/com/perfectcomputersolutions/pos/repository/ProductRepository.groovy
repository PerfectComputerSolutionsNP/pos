package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.Product
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository extends ModelEntityRepository<Product, Long> {
}
