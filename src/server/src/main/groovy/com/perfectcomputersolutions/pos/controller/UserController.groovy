package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.User
import com.perfectcomputersolutions.pos.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController extends PrivilegedCrudController<User, Long> {

    @Autowired UserService service
}
