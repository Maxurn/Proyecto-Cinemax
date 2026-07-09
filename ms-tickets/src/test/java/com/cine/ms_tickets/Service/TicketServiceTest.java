package com.cine.ms_tickets.Service;

import com.cine.ms_tickets.Model.Ticket;
import com.cine.ms_tickets.Repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository repository;

    @InjectMocks
    private TicketService ticketService;

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        ticket = new Ticket();
        ticket.setId(1);
        ticket.setReservaId(10);
        ticket.setCodigoQr("TKT-123456");
        ticket.setEstado("ACTIVO");
    }

    @Test
    void generarTicket_creaTicketConCodigoQrYEstadoActivo() {
        when(repository.save(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Ticket resultado = ticketService.generarTicket(10);

        assertNotNull(resultado);
        assertEquals(10, resultado.getReservaId());
        assertEquals("Activo", resultado.getEstado());
        assertTrue(resultado.getCodigoQr().startsWith("TKT-"));
        verify(repository, times(1)).save(any(Ticket.class));
    }

    @Test
    void validarTicket_conCodigoActivo_retornaTicket() {
        when(repository.findByCodigoQr("TKT-123456")).thenReturn(ticket);

        Ticket resultado = ticketService.validarTicket("TKT-123456");

        assertNotNull(resultado);
        assertEquals("TKT-123456", resultado.getCodigoQr());
    }

    @Test
    void validarTicket_conCodigoUsado_retornaNull() {
        ticket.setEstado("USADO");
        when(repository.findByCodigoQr("TKT-123456")).thenReturn(ticket);

        Ticket resultado = ticketService.validarTicket("TKT-123456");

        assertNull(resultado);
    }

    @Test
    void validarTicket_conCodigoInexistente_retornaNull() {
        when(repository.findByCodigoQr("TKT-NOEXISTE")).thenReturn(null);

        Ticket resultado = ticketService.validarTicket("TKT-NOEXISTE");

        assertNull(resultado);
    }
}