package com.cine.ms_sucursal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cine.ms_sucursal.Model.Cine;
import com.cine.ms_sucursal.Model.Sala;
import com.cine.ms_sucursal.Repository.CineRepository;
import com.cine.ms_sucursal.Repository.SalaRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private CineRepository cineRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Override
    public void run(String... args) throws Exception {
        
            Faker faker = new Faker();

            for (int i = 0; i < 3; i++) {
                Cine cine = new Cine();
                cine.setNombre("Cine" + faker.options().option("Mark", "Lumix", "Polís"));
                cine.setDireccion(faker.address().streetAddress());
                cine.setCiudad(faker.options().option("Santiago", "San Miguel", "Maipú", "Talca", "Puerto Montt"));
                
                Cine cineGuardado = cineRepository.save(cine);

                List<Sala> salas = new ArrayList<>(); //este es para crear + agregar salas
                for (int j = 1; j <= 3; j++) {
                    Sala sala = new Sala();
                    sala.setNombre(faker.options().option("Sala Comun", "Sala 3DX", "Sala Premiere") + j);
                    sala.setCapacidad(faker.number().numberBetween(35, 100));
                    sala.setCine(cineGuardado);
                    salas.add(sala);
                }
                salaRepository.saveAll(salas); 
            }
        }
    }

