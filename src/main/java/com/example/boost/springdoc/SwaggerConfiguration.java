package com.example.boost.springdoc;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@SecurityScheme(
        name= "bearerAuth",
        type= SecuritySchemeType.HTTP,
        bearerFormat= "JWT",
        scheme = "bearer"
)

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi BoostAPI() {
        return GroupedOpenApi.builder()
                .group("Boost API V1")
                .pathsToMatch("/api/v1/**")
                .build();
    }
}
