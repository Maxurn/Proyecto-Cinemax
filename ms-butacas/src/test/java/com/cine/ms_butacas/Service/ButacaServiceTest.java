package com.cine.ms_butacas.Service;

import com.cine.ms_butacas.Client.SucursalClient;
import com.cine.ms_butacas.Model.Butaca;
import com.cine.ms_butacas.Model.DTO.SalaDTO;
import com.cine.ms_butacas.Repository.ButacaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ButacaServiceTest {

    @Mock
    private ButacaRepository butacaRepository;

    @Mock
    private SucursalClient sucursalClient;

    @InjectMocks
    private ButacaService butacaService;

    private Butaca butaca;

    @BeforeEach
    void setUp() {
        butaca = new Butaca();
        butaca.setId(1);
        butaca.setFuncionId(5);
        butaca.setSalaId(2);
        butaca.setNumero(14);
        butaca.setStatus("FREE");
        butaca.setLockedUntil(null);
    }

    @Test
    void getButacasPorFuncion_siYaExisten_retornaButacasExistentes() {
        List<Butaca> existentes = Arrays.asList(butaca);
        when(butacaRepository.existsByFuncionId(5)).thenReturn(true);
        when(butacaRepository.findByFuncionId(5)).thenReturn(existentes);

        List<Butaca> resultado = butacaService.getButacasPorFuncion(5, 2);

        assertEquals(1, resultado.size());
        verify(sucursalClient, never()).buscarPorIdC(any());
    }

    @Test
    void getButacasPorFuncion_siNoExisten_creaButacasSegunCapacidad() {
        SalaDTO sala = new SalaDTO();
        sala.setId(2);
        sala.setCapacidad(3);

        when(butacaRepository.existsByFuncionId(5)).thenReturn(false);
        when(sucursalClient.buscarPorIdC(2)).thenReturn(sala);
        when(butacaRepository.saveAll(any())).thenAnswer(invocation -> invocation.getArgument(0));

        List<Butaca> resultado = butacaService.getButacasPorFuncion(5, 2);

        assertEquals(3, resultado.size());
        assertEquals("FREE", resultado.get(0).getStatus());
        verify(butacaRepository, times(1)).saveAll(any());
    }

    @Test
    void getButacasPorFuncion_siSalaNoExiste_retornaListaVacia() {
        when(butacaRepository.existsByFuncionId(5)).thenReturn(false);
        when(sucursalClient.buscarPorIdC(2)).thenReturn(null);

        List<Butaca> resultado = butacaService.getButacasPorFuncion(5, 2);

        assertTrue(resultado.isEmpty());
        verify(butacaRepository, never()).saveAll(any());
    }

    @Test
    void lockButaca_conButacaLibre_laBloqueaPor15Minutos() {
        when(butacaRepository.findById(1)).thenReturn(Optional.of(butaca));
        when(butacaRepository.save(any(Butaca.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Butaca resultado = butacaService.lockButaca(1);

        assertNotNull(resultado);
        assertEquals("LOCKED", resultado.getStatus());
        assertNotNull(resultado.getLockedUntil());
    }

    @Test
    void lockButaca_conButacaInexistente_retornaNull() {
        when(butacaRepository.findById(99)).thenReturn(Optional.empty());

        Butaca resultado = butacaService.lockButaca(99);

        assertNull(resultado);
    }

    @Test
    void lockButaca_conButacaYaVendida_retornaNull() {
        butaca.setStatus("BOOKED");
        when(butacaRepository.findById(1)).thenReturn(Optional.of(butaca));

        Butaca resultado = butacaService.lockButaca(1);

        assertNull(resultado);
        verify(butacaRepository, never()).save(any());
    }

    @Test
    void lockButaca_conButacaBloqueadaYVigente_retornaNull() {
        butaca.setStatus("LOCKED");
        butaca.setLockedUntil(LocalDateTime.now().plusMinutes(10));
        when(butacaRepository.findById(1)).thenReturn(Optional.of(butaca));

        Butaca resultado = butacaService.lockButaca(1);

        assertNull(resultado);
        verify(butacaRepository, never()).save(any());
    }

    @Test
    void bookButaca_conButacaDisponible_laMarcaComoVendida() {
        when(butacaRepository.findById(1)).thenReturn(Optional.of(butaca));
        when(butacaRepository.save(any(Butaca.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Butaca resultado = butacaService.bookButaca(1);

        assertNotNull(resultado);
        assertEquals("BOOKED", resultado.getStatus());
        assertNull(resultado.getLockedUntil());
    }

    @Test
    void bookButaca_conButacaInexistente_retornaNull() {
        when(butacaRepository.findById(99)).thenReturn(Optional.empty());

        Butaca resultado = butacaService.bookButaca(99);

        assertNull(resultado);
    }

    @Test
    void bookButaca_conButacaYaVendida_retornaNull() {
        butaca.setStatus("BOOKED");
        when(butacaRepository.findById(1)).thenReturn(Optional.of(butaca));

        Butaca resultado = butacaService.bookButaca(1);

        assertNull(resultado);
        verify(butacaRepository, never()).save(any());
    }
}