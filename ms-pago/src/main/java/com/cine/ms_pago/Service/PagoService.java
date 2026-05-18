package com.cine.ms_pago.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ms_pago.Model.Pago;
import com.cine.ms_pago.Model.DTO.PagoConfiteriaDTO;
import com.cine.ms_pago.Model.DTO.PagoReservaDTO;
import com.cine.ms_pago.Repository.PagoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public Pago procesarPago(PagoReservaDTO dto) {
        Pago pago = new Pago();
        pago.setReservaId(dto.getReservaId());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setFecha(LocalDate.now());

        if (dto.getMonto() > 0) {
            pago.setEstado("APROBADO");
        } else if (dto.getMonto() == 0) {
            pago.setEstado("PENDIENTE");
        } else {
            pago.setEstado("RECHAZADO");
        }
        return pagoRepository.save(pago);
    }


    public List<Pago> listarReservaId(Integer reservaId) {
        return pagoRepository.findByReservaId(reservaId);
    }

    public Pago procesarPagoConfiteria(PagoConfiteriaDTO dto) {
        Pago pago = new Pago();
        pago.setComboId(dto.getComboId());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setFecha(LocalDate.now());

        if (dto.getMonto() > 0) {
            pago.setEstado("APROBADO");
        } else if (dto.getMonto() == 0) {
            pago.setEstado("PENDIENTE");
        } else {
            pago.setEstado("RECHAZADO");
        }
        return pagoRepository.save(pago);
    }

    public List<Pago> listarComboId(Integer comboId) {
        return pagoRepository.findByComboId(comboId);
    }
}
