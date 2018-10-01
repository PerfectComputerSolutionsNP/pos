package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.Category
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository extends NamedEntityRepository<Category, Long> {
}
