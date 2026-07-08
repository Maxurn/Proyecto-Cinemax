package com.cine.ms_pago.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.cine.ms_pago.Model.Pago;
import com.cine.ms_pago.Model.DTO.PagoReservaDTO;
import com.cine.ms_pago.Repository.PagoRepository;

@ActiveProfiles("test")
@SpringBootTest
public class PagoServiceTest {

    @Autowired
    private PagoService pagoService;

    @MockitoBean
    private PagoRepository pagoRepository;

    @Test
    public void testProcesarPagoAprobado() {
        reset(pagoRepository);
        PagoReservaDTO dto = new PagoReservaDTO();
        dto.setReservaId(1);
        dto.setMonto(100.0);
        dto.setMetodoPago("CREDITO");

        when(pagoRepository.save(any(Pago.class))).thenAnswer(i -> i.getArguments()[0]);

        Pago resultado = pagoService.procesarPago(dto);


        assertNotNull(resultado);
        assertEquals("APROBADO", resultado.getEstado());
        assertEquals(1, resultado.getReservaId());
        verify(pagoRepository, times(1)).save(any(Pago.class));
    }

    @Test
    public void testListarReservaId() {
        Integer reservaId = 1;
        when(pagoRepository.findByReservaId(reservaId)).thenReturn(Arrays.asList(new Pago()));

        List<Pago> resultados = pagoService.listarReservaId(reservaId);

        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size());
    }
}
