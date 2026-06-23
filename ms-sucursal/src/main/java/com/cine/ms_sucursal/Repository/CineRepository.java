package com.cine.ms_sucursal.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cine.ms_sucursal.Model.Cine;
import java.util.List;

@Repository
public interface CineRepository extends JpaRepository<Cine, Integer>{

    List<Cine> findByCiudad(String ciudad);
}
