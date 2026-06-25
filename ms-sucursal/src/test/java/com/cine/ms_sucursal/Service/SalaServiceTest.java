package com.cine.ms_sucursal.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.cine.ms_sucursal.Model.Cine;
import com.cine.ms_sucursal.Model.Sala;
import com.cine.ms_sucursal.Repository.CineRepository;
import com.cine.ms_sucursal.Repository.SalaRepository;

@ActiveProfiles("test")
@SpringBootTest
public class SalaServiceTest {

    @Autowired
    private SalaService salaService;

    @MockitoBean
    private SalaRepository salaRepository;

    @MockitoBean
    private CineRepository cineRepository;

    @Test
    public void testListarTodasLasSalas() {
        when(salaRepository.findAll()).thenReturn(Arrays.asList(new Sala(), new Sala()));
        List<Sala> resultado = salaService.listarTodas();
        assertEquals(2, resultado.size());
        verify(salaRepository, times(1)).findAll();
    }

    @Test
    public void testAgregarSalaACineExitoso() {
        Integer cineId = 1;
        Cine cine = new Cine();
        Sala sala = new Sala();
        
        when(cineRepository.findById(cineId)).thenReturn(Optional.of(cine));
        when(salaRepository.save(any(Sala.class))).thenReturn(sala);

        Sala resultado = salaService.agregarSalaACine(cineId, sala);

        assertNotNull(resultado);
        verify(salaRepository, times(1)).save(sala);
        assertEquals(cine, sala.getCine());
    }

    @Test
    public void testBuscarSalaPorId() {
        Integer id = 1;
        Sala sala = new Sala();
        when(salaRepository.findById(id)).thenReturn(Optional.of(sala));
        
        Sala resultado = salaService.buscarPorId(id);
        
        assertNotNull(resultado);
        verify(salaRepository, times(1)).findById(id);
    }

    @Test
    public void testEliminarSala() {
        Integer id = 1;
        salaService.eliminar(id);
        verify(salaRepository, times(1)).deleteById(id);
    }
}
