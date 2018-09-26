package com.perfectcomputersolutions.pos

import com.perfectcomputersolutions.pos.util.CORSFilter
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean

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

  // https://www.youtube.com/watch?v=l1hazxgwLC0

  @Bean
  public FilterRegistrationBean corsFilterRegistration() {
    FilterRegistrationBean registrationBean =
            new FilterRegistrationBean(new CORSFilter());
    registrationBean.setName("CORS Filter");
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(1);
    return registrationBean;
  }

}
