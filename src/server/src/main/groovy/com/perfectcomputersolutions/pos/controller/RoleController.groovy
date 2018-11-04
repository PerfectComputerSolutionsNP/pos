package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("role")
class RoleController {

    @Autowired RoleService service

    @GetMapping
    def findAll(@RequestParam int page, @RequestParam int size) {

        CrudController.findAll(
                service,
                page,
                size,
                Optional.of(true),
                Optional.of("name")
        )
    }
}
