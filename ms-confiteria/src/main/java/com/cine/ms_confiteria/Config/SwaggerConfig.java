package com.cine.ms_confiteria.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Microservicio Confiteria")
                .version("1.0")
                .description("Documentacion de la API para el microservicio relacionado con la confiteria"));
    }
}