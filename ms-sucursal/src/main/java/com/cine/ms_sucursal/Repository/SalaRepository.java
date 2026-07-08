package com.cine.ms_sucursal.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cine.ms_sucursal.Model.Sala;
import java.util.List;


@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {

    List<Sala> findByCineId(Integer id);
}
