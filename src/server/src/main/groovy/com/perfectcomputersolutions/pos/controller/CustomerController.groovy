package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.Customer
import com.perfectcomputersolutions.pos.service.CustomerService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customer")
@Api(value="category", description='Operations pertaining to customers. This endpoint does not require any particular privileges.')
class CustomerController extends UnprivilegedCrudController<Customer, Long> {

    @Autowired CustomerService service
}
