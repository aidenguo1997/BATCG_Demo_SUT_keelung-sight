package com.sight.KeelungSight.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customApi() {
        return new OpenAPI()
                .info(new Info().title("Sight API")
                        .description("Spring Sight sample application")
                        .version("v1"));
    }
}

