package com.perfectcomputersolutions.pos.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration

import javax.annotation.PostConstruct

@Configuration
class LocaleConfig {

    private static final Logger log = LoggerFactory.getLogger(this.class)

    @PostConstruct
    void init() {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))

        log.info("Spring boot application running in UTC timezone : ${new Date()}")
    }
}
