package com.perfectcomputersolutions.pos.crud.controller

import com.perfectcomputersolutions.pos.crud.service.UserService
import com.perfectcomputersolutions.pos.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller for API calls related to users.
 *
 * @see User
 */
@RestController
@RequestMapping("/user")
class UserController extends CrudController<User, String> {

    @Autowired UserService service
}