package com.perfectcomputersolutios

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
class ApplicationSpec {
}
