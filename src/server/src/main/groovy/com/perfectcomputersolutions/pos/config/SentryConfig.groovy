package com.perfectcomputersolutions.pos.config

import io.sentry.spring.SentryExceptionResolver
import io.sentry.spring.SentryServletContextInitializer
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerExceptionResolver

@Configuration
class SentryConfig {

    @Bean
    HandlerExceptionResolver sentryExceptionResolver() {

        return new SentryExceptionResolver()
    }

    @Bean
    ServletContextInitializer sentryServletContextInitializer() {

        return new SentryServletContextInitializer()
    }
}
