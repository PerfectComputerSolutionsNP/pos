package com.perfectcomputersolutions.pos.crud.service

import com.perfectcomputersolutions.pos.crud.repository.CategoryRepository
import com.perfectcomputersolutions.pos.crud.validator.CategoryValidator
import com.perfectcomputersolutions.pos.model.Category

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service for managing categories in database.
 *
 * @see Category
 */
@Service
class CategoryService extends CrudService<Category, String> {

//    private static final Logger log = LoggerFactory.getLogger(CategoryService.class)

    @Autowired CategoryRepository repository
    @Autowired CategoryValidator  validator
}
