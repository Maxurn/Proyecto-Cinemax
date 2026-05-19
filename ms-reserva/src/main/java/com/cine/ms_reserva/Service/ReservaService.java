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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

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
            System.out.println("Usuario con id " + datosReserva.getUsuarioId() + " no encontrado.");
        }
        
        FuncionDTO funcion = carteleraClient.obtenerFuncion(datosReserva.getFuncionId());
        if (funcion == null) {
            System.out.println("Función con id " + datosReserva.getFuncionId() + " no encontrada.");
        }

        ButacaDTO butaca = butacaClient.reservarButaca(datosReserva.getButacaId());
        
        if (butaca == null || !butaca.getStatus().equals("BOOKED")) {
            System.out.println("No se pudo reservar la butaca con id " + datosReserva.getButacaId());
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

        PagoReservaDTO pagoDTO = new PagoReservaDTO(reserva.getId(), datosReserva.getMonto(), datosReserva.getMetodoPago());
        PagoRespuestaDTO pagoRespuesta = pagoClient.pagar(pagoDTO);

        if (pagoRespuesta.getEstado().equals("APROBADO")) {
            reserva.setEstado("CONFIRMADA");
        } else {
            reserva.setEstado("CANCELADA");
        }
        return reservaRepository.save(reserva);

    }

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public List<Reserva> obtenerReservasPorUsuario(int usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public Reserva obtenerPorId(int id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public Reserva actualizarEstado(int id, String nuevoEstado) {
        Reserva reserva = obtenerPorId(id);

        if (reserva == null) {
            System.out.println("Reserva no encontrada");
            return null;
        }

        if (nuevoEstado != null) {
            reserva.setEstado(nuevoEstado);
        }

        return reservaRepository.save(reserva);
    }

    public boolean eliminar(int id) {
        if (obtenerPorId(id) != null) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
