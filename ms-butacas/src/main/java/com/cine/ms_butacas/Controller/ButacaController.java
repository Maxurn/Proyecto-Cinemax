package com.cine.ms_butacas.Controller;

import com.cine.ms_butacas.Model.Butaca;
import com.cine.ms_butacas.Service.ButacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/v1/seats")
@Tag(name = "Butacas", description = "Gestion de butacas del cine")
public class ButacaController {

    @Autowired
    private ButacaService butacaService;

    @GetMapping("/show/{funcionId}")
    @Operation(summary = "Obtener butacas por funcion", description = "Retorna todas las butacas de una funcion y sala especifica")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Butacas obtenidas exitosamente"),
        @ApiResponse(responseCode = "404", description = "Sala o funcion no encontrada")
    })
    public ResponseEntity<List<Butaca>> getButacas(@PathVariable Integer funcionId, @RequestParam Integer salaId) {

        List<Butaca> butacas = butacaService.getButacasPorFuncion(funcionId, salaId);
        return ResponseEntity.ok(butacas);
    }

    @PutMapping("/{seatId}/lock")
    @Operation(summary = "Bloquear butaca", description = "Bloquea una butaca por 15 minutos")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Butaca bloqueada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Butaca no disponible")
    })
    public ResponseEntity<Butaca> lockSeat(@PathVariable Integer seatId) {
        Butaca butaca = butacaService.lockButaca(seatId);
        return ResponseEntity.ok(butaca);
    }


    @PutMapping("/{seatId}/book")
    @Operation(summary = "Reservar butaca", description = "Confirma la reserva de una butaca")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Butaca reservada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Butaca no disponible")
    })
    public ResponseEntity<Butaca> bookSeat(@PathVariable Integer seatId) {
        Butaca butaca = butacaService.bookButaca(seatId);
        return ResponseEntity.ok(butaca);
    }
}
