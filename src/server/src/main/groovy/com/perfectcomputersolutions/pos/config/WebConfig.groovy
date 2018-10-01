package com.perfectcomputersolutions.pos.config

import com.perfectcomputersolutions.pos.interceptor.HttpRequestInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Configures intercepts and Spring MVC Context
 */
@Configuration
class WebConfig implements WebMvcConfigurer {

    @Override
    void addInterceptors(InterceptorRegistry registry) {

        def interceptor = new HttpRequestInterceptor()

        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
    }
}