package com.cine.ms_peliculas;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cine.ms_peliculas.Model.Pelicula;
import com.cine.ms_peliculas.Repository.PeliculaRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner {

    private final OpenAPI customOpenAPI;
    @Autowired
    private PeliculaRepository repository;

    DataLoader(OpenAPI customOpenAPI) {
        this.customOpenAPI = customOpenAPI;
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Generar peliculas de ejemplo

        for (int i = 0; i < 10; i++) {
            Pelicula peli = new Pelicula();
            peli.setTituloCinta(faker.movie().name()); 
            peli.setRatingEdad(faker.options().option("ATP", "PG", "PG-13", "R"));
            peli.setDuracion(faker.number().numberBetween(100, 180));
            
            repository.save(peli);
        }
    }   
}
