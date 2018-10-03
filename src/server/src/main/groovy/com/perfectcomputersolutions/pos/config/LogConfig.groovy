package com.perfectcomputersolutions.pos.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter

/**
 * Configures logging for all HTTP requests.
 */
@Configuration
class LogConfig {

    // https://www.javadevjournal.com/spring/log-incoming-requests-spring/

    @Bean
    CommonsRequestLoggingFilter requestLoggingFilter() {

        def filter = new CommonsRequestLoggingFilter()

        filter.setIncludeClientInfo(true)
        filter.setIncludeQueryString(true)
        filter.setIncludePayload(true)

        return filter
    }
}
