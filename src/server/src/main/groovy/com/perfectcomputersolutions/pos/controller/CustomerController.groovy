package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.Customer
import com.perfectcomputersolutions.pos.service.CustomerService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("customer")
@Api(value="category", description='Operations pertaining to customers. This endpoint does not require any particular privileges.')
class CustomerController extends UnprivilegedCrudController<Customer, Long> {

    @Autowired CustomerService service

    @GetMapping("/search")
    def search(@RequestParam String param, @RequestParam int page, @RequestParam int size) {

        def body = service.search(param, page, size)

        respond(body, HttpStatus.OK)
    }
}
