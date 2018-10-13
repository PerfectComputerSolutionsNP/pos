package com.perfectcomputersolutions.pos

import com.perfectcomputersolutions.pos.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.scheduling.annotation.EnableAsync

/**
 * Starts main event loop for SpringBoot application.
 */
@EnableAsync
@SpringBootApplication
class Application implements CommandLineRunner {

  @Autowired CategoryRepository repository

  /**
   * Main event loop.
   */
  static void main(String... args) {

    SpringApplication.run(Application.class, args)
  }

  @Override
  void run(String... args) throws Exception {

    // Do stuff once application is fully initialized
  }
}
