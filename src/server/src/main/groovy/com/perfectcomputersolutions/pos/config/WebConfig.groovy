package com.perfectcomputersolutions.pos.config

import com.perfectcomputersolutions.pos.interceptor.HttpRequestInterceptor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Configures intercepts and Spring MVC Context
 */
@Configuration
class WebConfig implements WebMvcConfigurer {

    @Autowired HttpRequestInterceptor httpRequestInterceptor

    @Override
    void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(httpRequestInterceptor)
                .addPathPatterns("/**")
    }
}