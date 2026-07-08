package com.cine.ms_pago;

import com.cine.ms_pago.Model.Pago;
import com.cine.ms_pago.Repository.PagoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {
            Pago pago = new Pago();
            pago.setMonto(faker.number().randomDouble(2, 5, 50));
            pago.setMetodoPago(faker.options().option("CREDITO", "DEBITO", "TRANSFERENCIA"));
            pago.setFecha(LocalDate.now());
            pago.setEstado("APROBADO");
            
            if (i % 2 == 0) {
                pago.setReservaId(faker.number().numberBetween(100, 200));
            } else {
                pago.setComboId(faker.number().numberBetween(1, 10));
            }
            
            pagoRepository.save(pago);
        }
    }
}