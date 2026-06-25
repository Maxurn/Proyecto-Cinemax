package com.cine.ms_butacas.Config;

import com.cine.ms_butacas.Model.Butaca;
import com.cine.ms_butacas.Repository.ButacaRepository;
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
    public CommandLineRunner seedButacas(ButacaRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                log.info("Butacas ya existen, se omite el seeding.");
                return;
            }
            Faker faker = new Faker();
            for (int funcionId = 1; funcionId <= 2; funcionId++) {
                for (int i = 1; i <= 20; i++) {
                    Butaca butaca = new Butaca();
                    butaca.setFuncionId(funcionId);
                    butaca.setSalaId(faker.number().numberBetween(1, 3));
                    butaca.setNumero(i);
                    butaca.setStatus("FREE");
                    butaca.setLockedUntil(null);
                    repository.save(butaca);
                }
            }
            log.info("Se generaron butacas de prueba con Datafaker.");
        };
    }
}