package com.perfectcomputersolutions.pos

import com.perfectcomputersolutions.pos.repository.CategoryRepository
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

import java.lang.reflect.Method
import java.util.concurrent.Executor

@EnableAsync
@SpringBootApplication
class Application implements CommandLineRunner {

  @Autowired CategoryRepository repository

  static void main(String... args) {

    SpringApplication.run(Application.class, args)
  }

  @Override
  void run(String... args) throws Exception {

    // Do stuff once application is fully initialized
  }

}
