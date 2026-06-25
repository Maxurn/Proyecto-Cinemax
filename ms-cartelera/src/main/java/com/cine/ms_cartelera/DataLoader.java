package com.cine.ms_cartelera;

import com.cine.ms_cartelera.Model.Funcion;
import com.cine.ms_cartelera.Repository.FuncionRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FuncionRepository funcionRepository;

    @Override
    public void run(String... args) throws Exception {
            Faker faker = new Faker();

            for (int i = 0; i < 5; i++) {
                Funcion funcion = new Funcion();
                funcion.setPeliculaId(faker.number().numberBetween(1, 3));
                funcion.setSalaId(faker.number().numberBetween(1, 3));
                funcion.setFecha(LocalDate.now().plusDays(i));
                funcion.setHora(LocalTime.of(11 + i, 0));
                funcion.setPrecioEntrada(faker.options().option(6000.0, 3000.0));
                funcionRepository.save(funcion);
            }
        }
}
