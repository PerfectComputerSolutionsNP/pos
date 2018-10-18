package com.perfectcomputersolutios.controller

import com.perfectcomputersolutions.pos.controller.HealthCheckController
import org.junit.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebMvcTest(HealthCheckController.class)
class HealthControllerSpec extends Specification {

    // TODO - I'm pretty sure the test passing as-is is a false positive!

    def static final PATH = "/health"

    private MockMvc mvc

    def setup() {

        mvc = MockMvcBuilders
                .standaloneSetup(new HealthCheckController())
                .build()
    }

    @Test
    @WithMockUser("user")
    def "Get a 200"() {

        when: "HTTP - Get is allowed"

        def builder  = get(PATH)
        def response = mvc.perform(builder)
                          .andReturn()
                          .response

        then:
        response.status == HttpStatus.OK.value()
    }

}
