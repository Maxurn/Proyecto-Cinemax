package com.cine.ms_pago.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);

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
        log.info("Pago procesado y registrado");
        return pagoRepository.save(pago);
    }


    public List<Pago> listarReservaId(Integer reservaId) {
        log.info("Consultando pagos para la reserva");
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
        log.info("Pago de confitería procesado y registrado");
        return pagoRepository.save(pago);
    }

    public List<Pago> listarComboId(Integer comboId) {
        log.info("Consultando pagos para el comboId");
        return pagoRepository.findByComboId(comboId);
    }
}
