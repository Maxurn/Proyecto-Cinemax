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


@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @PostMapping("/generate")
    public ResponseEntity<Ticket> generar(@RequestBody int reservaId) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generarTicket(reservaId));
    }
    
    @GetMapping("/{Codigo}/validate")
    public ResponseEntity<?> validar(@PathVariable String codigo){
        Ticket ticket = service.validarTicket(codigo);
        if (ticket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Ticket inválido o ya utilizado.");
        }
        return ResponseEntity.ok(ticket);
    }

}
