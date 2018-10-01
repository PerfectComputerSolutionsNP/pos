package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.model.Category
import com.perfectcomputersolutions.pos.repository.CategoryRepository
import com.perfectcomputersolutions.pos.repository.NamedEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService extends NamedEntityService<Category, Long> {

    @Autowired CategoryRepository repository

    NamedEntityRepository<Category, Long> getRepository() {

        return this.repository
    }
}

