package com.cine.ms_tickets.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cine.ms_tickets.Model.Ticket;
import com.cine.ms_tickets.Repository.TicketRepository;

@Service
public class TicketService {

    private static final Logger log = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository repository;

    public Ticket generarTicket(Integer reservaId){
        Ticket ticket = new Ticket();
        ticket.setReservaId(reservaId);
        ticket.setCodigoQr("TKT-" +System.currentTimeMillis());
        ticket.setEstado("Activo");
        log.info("Generando ticket para reserva {}", reservaId);
        return repository.save(ticket);
    }

    public Ticket validarTicket(String codigo) {
    Ticket ticket = repository.findByCodigoQr( codigo);
    if (ticket != null && "ACTIVO".equalsIgnoreCase(ticket.getEstado())) {
        log.info("Ticket {} validado correctamente", codigo);
        return ticket;
    }
    log.warn("Ticket {} inválido o ya utilizado", codigo);
    return null; 
}

}
