package com.cine.ms_peliculas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.cine.ms_peliculas.Model.Pelicula;
import com.cine.ms_peliculas.Repository.PeliculaRepository;

import jakarta.transaction.Transactional;

@Profile("test")
@Component
public class DataLoaderTests implements CommandLineRunner{

    @Autowired
    private PeliculaRepository repository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Pelicula peli = new Pelicula();
        peli.setTituloCinta("Test Movie");
        peli.setRatingEdad("PG");
        peli.setDuracion(120);
        
        repository.save(peli);
    }

}
