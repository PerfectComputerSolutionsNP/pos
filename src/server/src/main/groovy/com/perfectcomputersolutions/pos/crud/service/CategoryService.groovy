package com.perfectcomputersolutions.pos.crud.service

import com.perfectcomputersolutions.pos.crud.repository.CategoryRepository
import com.perfectcomputersolutions.pos.crud.validator.CategoryValidator
import com.perfectcomputersolutions.pos.model.Category
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.security.InvalidParameterException

/**
 * Service for managing categories in database.
 *
 * @see Category
 */
@Service
class CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class)

    @Autowired
    CategoryRepository repository

    List<Category> findAll() {

        log.info("Finding all categories")

        return repository.findAll()
    }

    def create(Category category) {

        if (CategoryValidator.valid(category))
            return repository.save(category)

        else
            throw new InvalidParameterException("Invalid category")

    }
}
