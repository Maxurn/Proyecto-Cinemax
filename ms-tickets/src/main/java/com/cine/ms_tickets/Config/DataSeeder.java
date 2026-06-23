package com.cine.ms_tickets.Config;

import com.cine.ms_tickets.Model.Ticket;
import com.cine.ms_tickets.Repository.TicketRepository;
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
    public CommandLineRunner seedTickets(TicketRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                log.info("Tickets ya existen, se omite el seeding.");
                return;
            }
            Faker faker = new Faker();
            for (int i = 1; i <= 10; i++) {
                Ticket ticket = new Ticket();
                ticket.setReservaId(i);
                ticket.setCodigoQr("TKT-" + faker.number().digits(10));
                ticket.setEstado("ACTIVO");
                repository.save(ticket);
            }
            log.info("Se generaron 10 tickets de prueba con Datafaker.");
        };
    }
}