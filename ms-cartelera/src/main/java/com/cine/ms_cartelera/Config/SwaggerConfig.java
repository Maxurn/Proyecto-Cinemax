package com.cine.ms_cartelera.Config;

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
                        .title("MS - Cartelera")
                        .version("1.0")
                        .description("Microservicio dedicado a gestionar la cartelera de las peliculas y sus funciones (incluye cines y salas)"));
    }
}
