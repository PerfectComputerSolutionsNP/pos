package com.perfectcomputersolutions.pos.config

import com.perfectcomputersolutions.pos.security.JwtAuthenticationEntryPoint
import com.perfectcomputersolutions.pos.security.JwtAuthorizationTokenFilter
import com.perfectcomputersolutions.pos.security.JwtUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * Configuration for Spring Security.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private JwtAuthenticationEntryPoint unauthorizedHandler
    @Autowired private JwtUserDetailsService       jwtUserDetailsService
    @Autowired JwtAuthorizationTokenFilter         authenticationTokenFilter

    @Value('${jwt.header}')
    private String tokenHeader

    @Value('${jwt.route.authentication.path}')
    private String authenticationPath

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(jwtUserDetailsService)
            .passwordEncoder(passwordEncoderBean())
    }

    @Bean
    PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder()
    }

    @Bean
    @Override
    AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean()
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        final CORS_CONFIG_PATH = "/**"
        final ALLOWED_ORIGINS  = ["*"]
        final ALLOWED_HEADERS  = ["Authorization", "Cache-Control", "Content-Type"]
        final ALLOWED_METHODS  = [
                "HEAD",
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "PATCH"
        ]

        final configuration = new CorsConfiguration()

        configuration.setAllowCredentials(true)
        configuration.setAllowedOrigins(ALLOWED_ORIGINS)
        configuration.setAllowedMethods(ALLOWED_METHODS)
        configuration.setAllowedHeaders(ALLOWED_HEADERS)

        final source = new UrlBasedCorsConfigurationSource()

        source.registerCorsConfiguration(CORS_CONFIG_PATH, configuration)

        return source
    }

    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth")
                .permitAll()
                .anyRequest()
                .authenticated();


        http.addFilterBefore(
                authenticationTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );
    }

    @Override
    void configure(WebSecurity web) throws Exception {

        String[] files = [
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
        ]

        String[] swagger = [

                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-resources/configuration/ui",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html"
        ]

        String[] patterns = files + swagger

        web.ignoring()
           .antMatchers(patterns)
    }
}
