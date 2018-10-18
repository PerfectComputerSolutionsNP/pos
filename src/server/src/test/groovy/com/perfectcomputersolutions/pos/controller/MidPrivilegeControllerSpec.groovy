package com.perfectcomputersolutions.pos.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WithMockUser(username = "test", password = "test", roles = "USER")
abstract class MidPrivilegeControllerSpec extends CrudControllerSpec {

}
