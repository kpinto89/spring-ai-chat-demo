package com.demo.ai.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Spring AI Chat Demo API",
        description = "OpenAPI definition for the Spring AI chat demo endpoints.",
        version = "v1"
))
public class OpenApiConfig {
}

