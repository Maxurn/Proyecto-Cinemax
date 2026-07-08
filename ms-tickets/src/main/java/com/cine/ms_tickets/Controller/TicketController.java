package com.cine.ms_tickets.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.cine.ms_tickets.Model.Ticket;
import com.cine.ms_tickets.Service.TicketService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/v1/tickets")
@Tag(name = "Tickets", description = "Gestion de tickets del cine")
public class TicketController {

    @Autowired
    private TicketService service;

    @PostMapping("/generate")
    @Operation(summary = "Generar ticket", description = "Genera un nuevo ticket para una reserva")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Ticket generado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Reserva inválida")
    })
    public ResponseEntity<Ticket> generar(@RequestBody Integer reservaId) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generarTicket(reservaId));
    }
    
    @GetMapping("/{codigo}/validate")
    @Operation(summary = "Validar ticket", description = "Valida un ticket por su código QR")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ticket válido y activo"),
        @ApiResponse(responseCode = "404", description = "Ticket inválido o ya utilizado")
    })
    public ResponseEntity<?> validar(@Parameter(description = "Código QR del ticket", required = true)@PathVariable String codigo){
        Ticket ticket = service.validarTicket(codigo);
        if (ticket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Ticket inválido o ya utilizado.");
        }
        return ResponseEntity.ok(ticket);
    }

}
