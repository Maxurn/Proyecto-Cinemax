package com.cine.ms_reserva.Service;

import com.cine.ms_reserva.Client.ButacaClient;
import com.cine.ms_reserva.Client.CarteleraClient;
import com.cine.ms_reserva.Client.PagoClient;
import com.cine.ms_reserva.Client.UsuarioClient;
import com.cine.ms_reserva.Model.DTO.*;
import com.cine.ms_reserva.Model.Reserva;
import com.cine.ms_reserva.Repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private CarteleraClient carteleraClient;

    @Mock
    private ButacaClient butacaClient;

    @Mock
    private PagoClient pagoClient;

    @InjectMocks
    private ReservaService reservaService;

    private ReservaRequestDTO requestDTO;
    private UsuarioDTO usuarioDTO;
    private FuncionDTO funcionDTO;
    private ButacaDTO butacaDTO;
    private Reserva reserva;

    @BeforeEach
    void setUp() {
        requestDTO = new ReservaRequestDTO(3, 5, 14, 4500.0, "TARJETA");

        usuarioDTO = new UsuarioDTO(3, "Juan", null, "Perez", "juan@cine.cl", "+56912345678", "12345678");

        funcionDTO = new FuncionDTO(5, 1, 2, null, null, 4500.0);

        butacaDTO = new ButacaDTO(14, 5, 2, 14, "BOOKED");

        reserva = new Reserva();
        reserva.setId(1);
        reserva.setUsuarioId(3);
        reserva.setFuncionId(5);
        reserva.setButacaId(14);
        reserva.setMonto(4500.0);
        reserva.setMetodoPago("TARJETA");
        reserva.setEstado("PENDIENTE");
    }

    @Test
    void crearReserva_conPagoAprobado_quedaConfirmada() {
        when(usuarioClient.obtenerUsuario(3)).thenReturn(usuarioDTO);
        when(carteleraClient.obtenerFuncion(5)).thenReturn(funcionDTO);
        when(butacaClient.reservarButaca(14)).thenReturn(butacaDTO);
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PagoRespuestaDTO pagoAprobado = new PagoRespuestaDTO(1, 1, 4500.0, "TARJETA", "APROBADO", null);
        when(pagoClient.pagar(any(PagoReservaDTO.class))).thenReturn(pagoAprobado);

        Reserva resultado = reservaService.crearReserva(requestDTO);

        assertNotNull(resultado);
        assertEquals("CONFIRMADA", resultado.getEstado());
        verify(reservaRepository, times(2)).save(any(Reserva.class));
    }

    @Test
    void crearReserva_conPagoRechazado_quedaCancelada() {
        when(usuarioClient.obtenerUsuario(3)).thenReturn(usuarioDTO);
        when(carteleraClient.obtenerFuncion(5)).thenReturn(funcionDTO);
        when(butacaClient.reservarButaca(14)).thenReturn(butacaDTO);
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PagoRespuestaDTO pagoRechazado = new PagoRespuestaDTO(1, 1, 4500.0, "TARJETA", "RECHAZADO", null);
        when(pagoClient.pagar(any(PagoReservaDTO.class))).thenReturn(pagoRechazado);

        Reserva resultado = reservaService.crearReserva(requestDTO);

        assertNotNull(resultado);
        assertEquals("CANCELADA", resultado.getEstado());
    }

    @Test
    void crearReserva_llamaAPagoConElMontoYMetodoCorrectos() {
        when(usuarioClient.obtenerUsuario(3)).thenReturn(usuarioDTO);
        when(carteleraClient.obtenerFuncion(5)).thenReturn(funcionDTO);
        when(butacaClient.reservarButaca(14)).thenReturn(butacaDTO);
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PagoRespuestaDTO pagoAprobado = new PagoRespuestaDTO(1, 1, 4500.0, "TARJETA", "APROBADO", null);
        when(pagoClient.pagar(any(PagoReservaDTO.class))).thenReturn(pagoAprobado);

        reservaService.crearReserva(requestDTO);

        verify(pagoClient).pagar(argThat(dto ->
            dto.getMonto().equals(4500.0) && dto.getMetodoPago().equals("TARJETA")
        ));
    }

    @Test
    void listarTodas_retornaListaDeReservas() {
        when(reservaRepository.findAll()).thenReturn(Arrays.asList(reserva));

        List<Reserva> resultado = reservaService.listarTodas();

        assertEquals(1, resultado.size());
        verify(reservaRepository, times(1)).findAll();
    }

    @Test
    void obtenerReservasPorUsuario_retornaReservasDelUsuario() {
        when(reservaRepository.findByUsuarioId(3)).thenReturn(Arrays.asList(reserva));

        List<Reserva> resultado = reservaService.obtenerReservasPorUsuario(3);

        assertEquals(1, resultado.size());
        verify(reservaRepository, times(1)).findByUsuarioId(3);
    }

    @Test
    void obtenerPorId_conIdExistente_retornaReserva() {
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));

        Reserva resultado = reservaService.obtenerPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }

    @Test
    void obtenerPorId_conIdInexistente_retornaNull() {
        when(reservaRepository.findById(99)).thenReturn(Optional.empty());

        Reserva resultado = reservaService.obtenerPorId(99);

        assertNull(resultado);
    }

    @Test
    void actualizarEstado_conReservaExistente_actualizaEstado() {
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Reserva resultado = reservaService.actualizarEstado(1, "CANCELADA");

        assertEquals("CANCELADA", resultado.getEstado());
    }

    @Test
    void actualizarEstado_conReservaInexistente_retornaNull() {
        when(reservaRepository.findById(99)).thenReturn(Optional.empty());

        Reserva resultado = reservaService.actualizarEstado(99, "CANCELADA");

        assertNull(resultado);
        verify(reservaRepository, never()).save(any());
    }

    @Test
    void eliminar_conIdExistente_retornaTrue() {
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));

        boolean resultado = reservaService.eliminar(1);

        assertTrue(resultado);
        verify(reservaRepository, times(1)).deleteById(1);
    }

    @Test
    void eliminar_conIdInexistente_retornaFalse() {
        when(reservaRepository.findById(99)).thenReturn(Optional.empty());

        boolean resultado = reservaService.eliminar(99);

        assertFalse(resultado);
        verify(reservaRepository, never()).deleteById(any());
    }
}