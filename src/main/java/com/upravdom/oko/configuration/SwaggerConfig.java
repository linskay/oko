package com.upravdom.oko.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${app.version}") // Чтение версии из application.properties
    private String appVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OKO Backend API")
                        .version(appVersion) // Использование версии из properties
                        .description("API для управления информацией о стажерах и городах")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    public GroupedOpenApi cityApi() {
        return GroupedOpenApi.builder()
                .group("Cities Management")
                .pathsToMatch("/cities/**")
                .build();
    }

    @Bean
    public GroupedOpenApi healthCheckApi() {
        return GroupedOpenApi.builder()
                .group("Health Controller")
                .pathsToMatch("/health/**")
                .build();
    }

    @Bean
    public GroupedOpenApi traineeApi() {
        return GroupedOpenApi.builder()
                .group("Trainees Management")
                .pathsToMatch("/trainees/**")
                .build();
    }
}