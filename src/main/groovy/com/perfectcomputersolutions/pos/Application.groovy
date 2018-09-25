package com.perfectcomputersolutions.pos

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Starts main event loop for SpringBoot application.
 */
@SpringBootApplication
class Application {

  /**
   * Main event loop.
   */
  static void main(String[] args) {

    SpringApplication.run(Application.class, args)
  }

}
