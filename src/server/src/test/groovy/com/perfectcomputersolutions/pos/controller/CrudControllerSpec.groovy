package com.perfectcomputersolutions.pos.controller

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import spock.lang.Specification

import org.apache.http.client.fluent.Request

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class CrudControllerSpec extends Specification {

    @LocalServerPort
    int port

    abstract String getPath()

    def "Get a 401 for not being authorized"() {

        when: "Making a request without an authenticated user"

        def code = Request.Get("http://localhost:${port}/${path}")
                          .execute()
                          .returnResponse()
                          .statusLine
                          .statusCode

        then:
        code == HttpStatus.UNAUTHORIZED.value()
    }
}
