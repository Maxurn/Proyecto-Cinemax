package com.cine.ms_confiteria;

import com.cine.ms_confiteria.Model.Combo;
import com.cine.ms_confiteria.Repository.ComboRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ComboRepository comboRepository;

    @Override
    public void run(String... args) throws Exception {
        if (comboRepository.count() == 0) {
            Faker faker = new Faker();

            for (int i = 0; i < 5; i++) {
                Combo combo = new Combo();
                combo.setNombre(faker.food().dish());
                combo.setValor(faker.number().randomDouble(2, 3000, 15000));
                combo.setStock(faker.number().numberBetween(1, 25));
                combo.setCategoria(faker.options().option("Palomitas", "Ninio", "Promo", "Bebidas"));
                
                comboRepository.save(combo);
            }
            System.out.println("--- Datos de confitería cargados exitosamente ---");
        }
    }
}
