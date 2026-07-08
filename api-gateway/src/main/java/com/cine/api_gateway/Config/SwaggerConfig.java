package com.cine.api_gateway.Config;

import java.util.LinkedHashSet;
import java.util.Set;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class SwaggerConfig {

    private final SwaggerUiConfigProperties swaggerUiConfigProperties;

    public SwaggerConfig(SwaggerUiConfigProperties swaggerUiConfigProperties) {
        this.swaggerUiConfigProperties = swaggerUiConfigProperties;
    }

    @PostConstruct
    public void init() {

        Set<SwaggerUrl> urls = new LinkedHashSet<>();

        urls.add(new SwaggerUrl("ms-peliculas", "/api-docs/peliculas", "ms-peliculas"));
        urls.add(new SwaggerUrl("ms-sucursal", "/api-docs/sucursal", "ms-sucursal"));
        urls.add(new SwaggerUrl("ms-cartelera", "/api-docs/cartelera", "ms-cartelera"));
        urls.add(new SwaggerUrl("ms-usuario", "/api-docs/usuario", "ms-usuario"));
        urls.add(new SwaggerUrl("ms-butacas", "/api-docs/butacas", "ms-butacas"));
        urls.add(new SwaggerUrl("ms-reserva", "/api-docs/reserva", "ms-reserva"));
        urls.add(new SwaggerUrl("ms-pago", "/api-docs/pago", "ms-pago"));
        urls.add(new SwaggerUrl("ms-confiteria", "/api-docs/confiteria", "ms-confiteria"));
        urls.add(new SwaggerUrl("ms-tickets", "/api-docs/tickets", "ms-tickets"));
        urls.add(new SwaggerUrl("ms-notificacion", "/api-docs/notificacion", "ms-notificacion"));

        swaggerUiConfigProperties.setUrls(urls);
    }
}
