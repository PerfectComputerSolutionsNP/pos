package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.Category
import com.perfectcomputersolutions.pos.service.CategoryService
import com.perfectcomputersolutions.pos.service.NamedEntityService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("category")
@Api(value="category", description='Operations pertaining to categories. This controller requires admin rights to edit inventory but no rights to view it.')
class CategoryController extends InventoryController<Category, Long> {

    @Autowired CategoryService service

    NamedEntityService<Category, Long> getService() {
        return this.service
    }
}
