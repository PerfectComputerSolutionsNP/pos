package com.perfectcomputersolutions.pos.model

import spock.lang.Shared
import spock.lang.Specification

/**
 * Unit tests for the User entity.
 *
 * @see User
 */
class UserSpec extends Specification {

    @Shared User user

    /**
     * Instantiate user as a new user with default constructor
     */
    def setup() {

        user = new User()
    }

    def "Construct a user with default constructor"() {

        expect: "All fields null"
        user.firstName == null
        user.lastName  == null
    }

    def "Construct a user, change instance variables"() {

        when:
        def firstName = "firstName"
        def lastName  = "lastName"

        user.firstName = firstName
        user.lastName  = lastName

        then: "Check them"
        user.firstName == firstName
        user.lastName  == lastName
    }
}
