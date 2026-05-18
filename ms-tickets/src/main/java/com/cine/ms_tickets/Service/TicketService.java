package com.cine.ms_tickets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cine.ms_tickets.Model.Ticket;
import com.cine.ms_tickets.Repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository repository;

    public Ticket generarTicket(int reservaId){
        Ticket ticket = new Ticket();
        ticket.setReservaId(reservaId);
        ticket.setCodigoQr("TKT-" +System.currentTimeMillis());
        ticket.setEstado("Activo");
        return repository.save(ticket);
    }

    public Ticket validarTicket(String codigo) {
    Ticket ticket = repository.findByCodigoQr( codigo);
    if (ticket != null && "ACTIVO".equalsIgnoreCase(ticket.getEstado())) {
        return ticket;
    }
    return null; 
}

}
