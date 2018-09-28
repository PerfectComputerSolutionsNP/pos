package com.perfectcomputersolutions.pos.crud.controller

import com.perfectcomputersolutions.pos.crud.service.CategoryService
import com.perfectcomputersolutions.pos.model.Category

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller for API calls related to categories.
 *
 * @see Category
 */
@RestController
@RequestMapping("/category")
class CategoryController extends CrudController<Category, String> {

//    private static final Logger log = LoggerFactory.getLogger(CategoryController.class)

    @Autowired CategoryService service
}
