package com.cine.ms_pago.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cine.ms_pago.Model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer>{

    List<Pago> findByReservaId(Integer reservaId);

    List<Pago> findByComboId(Integer reservaId);

}
