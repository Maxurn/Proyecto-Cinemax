package com.cine.ms_cartelera.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.cine.ms_cartelera.Client.CineClient;
import com.cine.ms_cartelera.Client.PeliculaClient;
import com.cine.ms_cartelera.Model.Funcion;
import com.cine.ms_cartelera.Model.DTO.CineDTO;
import com.cine.ms_cartelera.Model.DTO.FuncionDetalleDTO;
import com.cine.ms_cartelera.Model.DTO.PeliculaDTO;
import com.cine.ms_cartelera.Model.DTO.SalaDTO;
import com.cine.ms_cartelera.Repository.FuncionRepository;

@ActiveProfiles("test")
@SpringBootTest
public class FuncionServiceTest {

    @Autowired
    private FuncionService funcionService;

    @MockitoBean
    private FuncionRepository repository;

    @MockitoBean
    private PeliculaClient peliClient;

    @MockitoBean
    private CineClient cineClient;

    @Test
    public void testObtenerDetalleExitoso() {
        Integer id = 1;
        Funcion funcion = new Funcion();
        funcion.setId(id);
        funcion.setPeliculaId(10);
        funcion.setSalaId(5);
        funcion.setFecha(LocalDate.now());
        funcion.setHora(LocalTime.now());

        PeliculaDTO peli = new PeliculaDTO();
        peli.setTituloCinta("Inception");

        CineDTO cine = new CineDTO();
        cine.setNombre("Cine Hoyts");

        SalaDTO sala = new SalaDTO();
        sala.setNombre("Sala 1");
        sala.setCine(cine);

        when(repository.findById(id)).thenReturn(Optional.of(funcion));
        when(peliClient.buscarPorIdC(10)).thenReturn(peli);
        when(cineClient.buscarPorIdC(5)).thenReturn(sala);

        FuncionDetalleDTO resultado = funcionService.obtenerDetalle(id);

        // 4. Verificación
        assertNotNull(resultado);
        assertEquals("Inception", resultado.getNombrePelicula());
        assertEquals("Cine Hoyts", resultado.getNombreCine());
    }

    @Test
    public void testNuevaFuncion() {
        Funcion funcion = new Funcion();
        funcion.setPeliculaId(1);
        when(repository.save(any(Funcion.class))).thenReturn(funcion);

        Funcion resultado = funcionService.nuevaFuncion(funcion);
        assertNotNull(resultado);
        verify(repository, times(1)).save(funcion);
    }
}