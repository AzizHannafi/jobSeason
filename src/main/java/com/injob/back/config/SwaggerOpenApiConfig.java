package com.injob.back.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@OpenAPIDefinition(servers = {@Server(url = "${application.swagger.url}")})
@ComponentScan(basePackages = "com.injob.back")
public class SwaggerOpenApiConfig implements WebMvcConfigurer {
/*
    @Value("${application.swagger.title}")
    private String applicationTitle;
    @Value("${application.swagger.description}")
    private String applicationDescription;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(applicationTitle)
                        .description(applicationDescription))
                .components(new Components()).paths(new Paths());
    }
*/
   /* @Bean
    public GroupedOpenApi inJobApis() {
        return GroupedOpenApi.builder().addOpenApiCustomizer(openApi -> openApi.getInfo().setTitle("InJob API"))
                .addOpenApiCustomizer(openApi -> openApi.getInfo().setDescription("All endpoints for INJOB client" ))
                .group("mobile")
                //.packagesToScan("com.injob.back.controller")
                .build();
    }*/
}
