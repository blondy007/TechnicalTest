package com.example.technicaltest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration  {

    @Bean
    public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
        .groupName("com.example.technicaltest")
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
    }

    private ApiInfo apiInfo() {
      return new ApiInfoBuilder().title("Technical Test Service")
              .description("Module for managing Technical Test Service").version("1.0.0").build();
    }
}
