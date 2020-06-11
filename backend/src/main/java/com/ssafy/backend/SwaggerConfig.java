package com.ssafy.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport{
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("API")
            .description("api swagger")
            .build();
    }

    @Override
     public void addResourceHandlers(final ResourceHandlerRegistry registry) {
       // Make Swagger meta-data available via <baseURL>/v2/api-docs/
       registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
       // Make Swagger UI available via <baseURL>/swagger-ui.html
       registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/");
     }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("example")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.ssafy.backend.controller"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }
}