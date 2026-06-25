package com.cine.ms_cartelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cine.ms_cartelera.Model.Funcion;
import java.util.List;


@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Integer>{

    List<Funcion> findByPeliculaId(Integer peliculaId);
}
