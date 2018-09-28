package com.perfectcomputersolutions.pos.config

import com.perfectcomputersolutions.pos.crud.service.UserService
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // https://medium.com/@gtommee97/rest-authentication-with-spring-security-and-mongodb-28c06da25fb1

    @Autowired
    UserService userService

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*

        Users should be able to:

        Log in, log out, read-only access on products and categories, perform transactions,
        change it's own password

        Admins:

        Everything a user can do, read/write access on products, categories, see sales data,
        modify / delete other users

         */

        // TODO - Disable authorization for OPTIONS pre-flight request

        // https://stackoverflow.com/questions/40405838/cors-preflight-request-in-spring-data-rest

        http.csrf()
            .disable()
            .authorizeRequests()
//            .antMatchers(HttpMethod.OPTIONS, "/*").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .sessionManagement()
            .disable()
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder()
    }

    @Override
    void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService)
    }
}