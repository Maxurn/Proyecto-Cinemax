package com.cine.ms_reserva.Controller;

import com.cine.ms_reserva.Model.Reserva;
import com.cine.ms_reserva.Model.DTO.ReservaRequestDTO;
import com.cine.ms_reserva.Service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaRequestDTO reserva) {
        Reserva nuevaReserva = reservaService.crearReserva(reserva);
        if (nuevaReserva == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarTodas() {
        return ResponseEntity.ok(reservaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerPorId(@PathVariable int id) {
        Reserva reserva = reservaService.obtenerPorId(id);
        if (reserva == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reserva>> obtenerReservasPorUsuario(@PathVariable int userId) {
        List<Reserva> reservas = reservaService.obtenerReservasPorUsuario(userId);
        return ResponseEntity.ok(reservas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarEstado(@PathVariable int id, @RequestParam String nuevoEstado) {
        Reserva reservaActualizada = reservaService.actualizarEstado(id, nuevoEstado);
        if (reservaActualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(reservaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        boolean eliminado = reservaService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}