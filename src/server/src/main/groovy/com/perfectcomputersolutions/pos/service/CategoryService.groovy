package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.model.Category
import com.perfectcomputersolutions.pos.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService extends CrudService<Category, Long> {

    @Autowired CategoryRepository repository
}
