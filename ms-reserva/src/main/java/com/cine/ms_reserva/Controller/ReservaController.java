package com.cine.ms_reserva.Controller;

import com.cine.ms_reserva.Model.Reserva;
import com.cine.ms_reserva.Model.DTO.ReservaRequestDTO;
import com.cine.ms_reserva.Service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Tag(name = "Reservas", description = "Gestion de reservas del cine")
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    @Operation(summary = "Crear reserva", description = "Crea una nueva reserva y procesa el pago")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos o pago rechazado")
    })
    public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaRequestDTO reserva) {
        Reserva nuevaReserva = reservaService.crearReserva(reserva);
        if (nuevaReserva == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }

    @GetMapping
    @Operation(summary = "Listar reservas", description = "Retorna todas las reservas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<List<Reserva>> listarTodas() {
        return ResponseEntity.ok(reservaService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar reservas", description = "Retorna todas las reservas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente") 
    public ResponseEntity<Reserva> obtenerPorId(@PathVariable int id) {
        Reserva reserva = reservaService.obtenerPorId(id);
        if (reserva == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener reservas por usuario")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<List<Reserva>> obtenerReservasPorUsuario(@PathVariable int userId) {
        List<Reserva> reservas = reservaService.obtenerReservasPorUsuario(userId);
        return ResponseEntity.ok(reservas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estado de reserva")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Estado actualizado"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<Reserva> actualizarEstado(@PathVariable int id, @RequestParam String nuevoEstado) {
        Reserva reservaActualizada = reservaService.actualizarEstado(id, nuevoEstado);
        if (reservaActualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(reservaActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reserva")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Reserva eliminada"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        boolean eliminado = reservaService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}