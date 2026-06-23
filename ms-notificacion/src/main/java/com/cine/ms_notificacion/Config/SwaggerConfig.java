package com.cine.ms_notificacion.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
            .title("API 2026 Notificaciones Cine")
            .version("1.0")
            .description("Documentacion API sistema de notificaciones"));
    }

}
