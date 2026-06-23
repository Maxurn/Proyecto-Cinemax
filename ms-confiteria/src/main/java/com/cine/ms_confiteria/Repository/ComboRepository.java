package com.cine.ms_confiteria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cine.ms_confiteria.Model.Combo;
import java.util.List;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Integer>{

    List<Combo> findByCategoria(String categoria);

}
