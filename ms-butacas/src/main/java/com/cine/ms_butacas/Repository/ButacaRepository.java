package com.cine.ms_butacas.Repository;

import com.cine.ms_butacas.Model.Butaca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ButacaRepository extends JpaRepository<Butaca, Integer> {

    List<Butaca> findByFuncionId(Integer funcionId);

    boolean existsByFuncionId(Integer funcionId);
}
