package com.perfectcomputersolutions.pos.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.model.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class CategoryServiceSpec extends Specification {

    @Shared    ObjectMapper    mapper
    @Autowired CategoryService service

    def setup() {

        mapper = new ObjectMapper()
    }

    @Unroll
    def "Create an invalid category"() {

        when:
        def payload = mapper.convertValue(map, Category)

        service.save(payload)

        then:
        thrown ValidationException

        where:
        map               || _
        [ "name" : null ] || _
        [ "name" : ""]    || _
        [ "id" : 1]       || _
    }

    @Unroll
    def "Create a valid category"() {

        when:
        def payload  = mapper.convertValue(map, Category)
        def category = service.save(payload)

        then:
        category.name == payload.name
        category.id  != null

        where:
        map               || _
        [ "name" : "jd" ] || _

    }
}
