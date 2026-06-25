package com.cine.ms_tickets.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cine.ms_tickets.Model.Ticket;



public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    // Consulta automática para buscar por el código QR
    Ticket findByCodigoQr(String codigoQr);
}
