package com.cine.ms_usuario.config;

import com.cine.ms_usuario.model.Usuario;
import com.cine.ms_usuario.repository.UsuarioRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    @Bean
    public CommandLineRunner seedUsuarios(UsuarioRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                log.info("Ya existen usuarios en la BB.DD.");
                return;
            }
            Faker faker = new Faker();
            for (int i = 0; i < 10; i++) {
                Usuario usuario = new Usuario();
                usuario.setNombre(faker.name().firstName());
                usuario.setApellido(faker.name().lastName());
                usuario.setCorreo(faker.internet().emailAddress());
                usuario.setTelefono(faker.phoneNumber().phoneNumber());
                usuario.setRut(faker.number().digits(8));
                repository.save(usuario);
            }
            log.info("Se generaron 10 usuarios de prueba!");
        };
    }
}