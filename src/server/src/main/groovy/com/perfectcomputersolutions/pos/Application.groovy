package com.perfectcomputersolutions.pos

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync

/**
 * Starts main event loop for SpringBoot application.
 */
@EnableAsync
@SpringBootApplication
class Application {

  /**
   * Main event loop.
   */
  static void main(String[] args) {

    SpringApplication.run(Application.class, args)
  }

}
