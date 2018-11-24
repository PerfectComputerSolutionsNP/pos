package com.perfectcomputersolutions.pos.config

import com.perfectcomputersolutions.pos.utility.Utility
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
class JpaConfig {

    @Bean
    AuditorAware<String> auditorAware() {

        return new AuditorAware<String>() {

            @Override
            Optional<String> getCurrentAuditor() {

                return Utility.currentUsername
            }
        }
    }
}
