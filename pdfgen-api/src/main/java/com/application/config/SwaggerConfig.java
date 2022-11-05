package com.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI crmOpenApi() {
        return new OpenAPI().info(new Info().title("PDF generation API")
                                            .description("REST API for generating PDF files from JSON templates")
                                            .license(new License().name("Apache 2.0")));
    }

}
