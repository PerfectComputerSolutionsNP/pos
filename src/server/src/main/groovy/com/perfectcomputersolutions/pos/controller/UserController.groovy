package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.User
import com.perfectcomputersolutions.pos.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
@Api(value="category", description='Operations pertaining to users. ADMIN rights are required for all operations.')
class UserController extends PrivilegedCrudController<User, Long> {

    @Autowired UserService service

    // TODO - Use CriteriaBuilder instead to abstract search concept to ALL entities

    @GetMapping("/exists/username")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Determine if a user exists by username. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def existsByUsername(@RequestParam String username) {

        boolean exists = service.existsByUsername(username)

        def body = [

                (MESSAGE) : exists ?
                        "A user exists with the username: "         + username :
                        "A user does not exist with the username: " + username,
                (EXISTS) : exists
        ]

        respond(body, HttpStatus.OK)
    }

    @GetMapping("/exists/email")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Determine if a user exists by email. This operation requires the ADMIN role.", authorizations = [@Authorization(value = "Bearer")])
    def existsByEmail(@RequestParam String email) {

        boolean exists = service.existsByEmail(email)

        def body = [

                (MESSAGE) : exists ?
                        "A user exists with the email: "         + email :
                        "A user does not exist with the email: " + email,
                (EXISTS) : exists
        ]

        respond(body, HttpStatus.OK)
    }

    @GetMapping('/username/{username}')
    @PreAuthorize("hasRole('ADMIN')")
    def findByUsername(@PathVariable String username) {

        def body = [
                (MESSAGE) : "User found",
                (CONTENT) : service.findByUsername(username)
        ]

        respond(body, HttpStatus.OK)
    }

}
