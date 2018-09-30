package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.Category
import com.perfectcomputersolutions.pos.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("category")
class CategoryController extends CrudController<Category, Long> {

    @Autowired CategoryService service;
}
