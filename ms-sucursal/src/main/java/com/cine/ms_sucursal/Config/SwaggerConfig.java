package com.cine.ms_sucursal.Config;

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
                .title("Microservicio de Sucursal")
                .version("1.0")
                .description("Documentación de la API para el M.S. de las Sucursales (Cines & Salas)"));
    }
}
