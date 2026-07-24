package com.danilpopov.taskmanager.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.Proxy;
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApiConfig() {

        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "Bearer",
                                        new SecurityScheme()
                                                .bearerFormat("JWT")
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                )
                )
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Bearer")
                );
    }
}