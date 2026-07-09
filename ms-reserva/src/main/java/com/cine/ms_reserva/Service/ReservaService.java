package com.cine.ms_reserva.Service;

import com.cine.ms_reserva.Client.ButacaClient;
import com.cine.ms_reserva.Client.CarteleraClient;
import com.cine.ms_reserva.Client.PagoClient;
import com.cine.ms_reserva.Client.UsuarioClient;
import com.cine.ms_reserva.Model.Reserva;
import com.cine.ms_reserva.Model.DTO.ButacaDTO;
import com.cine.ms_reserva.Model.DTO.FuncionDTO;
import com.cine.ms_reserva.Model.DTO.PagoReservaDTO;
import com.cine.ms_reserva.Model.DTO.PagoRespuestaDTO;
import com.cine.ms_reserva.Model.DTO.ReservaRequestDTO;
import com.cine.ms_reserva.Model.DTO.UsuarioDTO;
import com.cine.ms_reserva.Repository.ReservaRepository;
import lombok.RequiredArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private static final Logger log = LoggerFactory.getLogger(ReservaService.class);

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private CarteleraClient carteleraClient;

    @Autowired
    private ButacaClient butacaClient;

    @Autowired
    private PagoClient pagoClient;

    public Reserva crearReserva(ReservaRequestDTO datosReserva) {
        
        UsuarioDTO usuario = usuarioClient.obtenerUsuario(datosReserva.getUsuarioId());
        if (usuario == null) {
            log.warn("Usuario con id {} no encontrado.", datosReserva.getUsuarioId());
        }
        
        FuncionDTO funcion = carteleraClient.obtenerFuncion(datosReserva.getFuncionId());
        if (funcion == null) {
            log.warn("Función con id {} no encontrada.", datosReserva.getFuncionId());
        }

        ButacaDTO butaca = butacaClient.reservarButaca(datosReserva.getButacaId());
        
        if (butaca == null || !butaca.getStatus().equals("BOOKED")) {
            log.warn("No se pudo reservar la butaca con id {}", datosReserva.getButacaId());;
        }

        Reserva reserva = new Reserva();
        reserva.setUsuarioId(datosReserva.getUsuarioId());
        reserva.setFuncionId(datosReserva.getFuncionId());
        reserva.setButacaId(datosReserva.getButacaId());
        reserva.setMonto(datosReserva.getMonto());
        reserva.setMetodoPago(datosReserva.getMetodoPago());
        reserva.setEstado("PENDIENTE");
        reserva.setFechaReserva(LocalDate.now());
        reserva = reservaRepository.save(reserva);

        log.info("Reserva {} creada en estado PENDIENTE", reserva.getId());


        PagoReservaDTO pagoDTO = new PagoReservaDTO(reserva.getId(), datosReserva.getMonto(), datosReserva.getMetodoPago());
        PagoRespuestaDTO pagoRespuesta = pagoClient.pagar(pagoDTO);

        if (pagoRespuesta.getEstado().equals("APROBADO")) {
            reserva.setEstado("CONFIRMADA");
            log.info("Pago aprobado, reserva {} confirmada", reserva.getId());
        } else {
            reserva.setEstado("CANCELADA");
            log.warn("Pago rechazado, reserva {} cancelada", reserva.getId());
        }
        return reservaRepository.save(reserva);

    }

    public List<Reserva> listarTodas() {
        log.info("Listando todas las reservas");
        return reservaRepository.findAll();
    }

    public List<Reserva> obtenerReservasPorUsuario(int usuarioId) {
        log.info("Buscando reservas del usuario {}", usuarioId);
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public Reserva obtenerPorId(int id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public Reserva actualizarEstado(int id, String nuevoEstado) {
        Reserva reserva = obtenerPorId(id);

        if (reserva == null) {
            log.warn("Reserva {} no encontrada", id);
            return null;
        }

        if (nuevoEstado != null) {
            reserva.setEstado(nuevoEstado);
        }
         log.info("Reserva {} actualizada a estado {}", id, nuevoEstado);
        return reservaRepository.save(reserva);
    }

    public boolean eliminar(int id) {
        if (obtenerPorId(id) != null) {
            reservaRepository.deleteById(id);
            log.info("Reserva {} eliminada", id);
            return true;
        }
        log.warn("No se pudo eliminar, reserva {} no encontrada", id);
        return false;
    }
}
