package com.perfectcomputersolutions.pos.config

import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.Contact
import springfox.documentation.service.SecurityScheme
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    // https://stackoverflow.com/questions/37794571/how-do-you-turn-off-swagger-ui-in-production

    @Bean
    Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes())
    }

    private static ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Point of Sale API")
                .description("Please write \"Bearer \" (without quotes) before the authorization token.")
//                .license("Apache 2.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//                .termsOfServiceUrl("")
//                .version("1.0.0")
                .contact(new Contact("Perfect Computer Solutions", "https://github.com/PerfectComputerSolutionsNP/pos", "perfectcomputersolutionsnp@gmail.com"))
                .build()
    }

    private static ArrayList<? extends SecurityScheme> securitySchemes() {

        return [new ApiKey("Bearer", "Authorization", "header")] as ArrayList
    }
}