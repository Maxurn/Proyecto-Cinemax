package com.cine.ms_reserva.Config;

import com.cine.ms_reserva.Model.Reserva;
import com.cine.ms_reserva.Repository.ReservaRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataSeeder {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    @Bean
    public CommandLineRunner seedReservas(ReservaRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                log.info("Reservas ya existen, se omite el seeding.");
                return;
            }
            Faker faker = new Faker();
            List<String> metodos = List.of("TARJETA", "EFECTIVO");
            List<String> estados = List.of("PENDIENTE", "CONFIRMADA", "CANCELADA");

            for (int i = 1; i <= 10; i++) {
                Reserva reserva = new Reserva();
                reserva.setUsuarioId(faker.number().numberBetween(1, 10));
                reserva.setFuncionId(faker.number().numberBetween(1, 5));
                reserva.setButacaId(faker.number().numberBetween(1, 40));
                reserva.setMonto(4500.0);
                reserva.setMetodoPago(metodos.get(faker.number().numberBetween(0, metodos.size())));
                reserva.setEstado(estados.get(faker.number().numberBetween(0, estados.size())));
                reserva.setFechaReserva(LocalDate.now());
                repository.save(reserva);
            }
            log.info("Se generaron 10 reservas de prueba con Datafaker.");
        };
    }
}