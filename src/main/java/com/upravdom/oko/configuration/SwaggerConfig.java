package com.upravdom.oko.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${spring.application.name}")
    private String appName;

    @Value("${build.version}")
    private String appVersion;

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .description("API для управления стажерами")
                        .version(appVersion)
                        .license(new License().name("Apache 2.0").url("https://springdoc.org/")));
    }
}
