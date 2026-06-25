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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.cine.ms_sucursal.Model.Cine;
import com.cine.ms_sucursal.Repository.CineRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class) //Lo utilize debido a que @SpringBootTest no funcionaba con DataFaker/DataLoader (Lo Ejecutaba automaticamente)
public class CineServiceTest {

    @InjectMocks //En vez de @Autowired utilizo este por el @ExtendWith
    private CineService cineService;

    @Mock //En vez de @MockitoBean se usa @Mock
    private CineRepository repository;

    @Test
    public void testListarCines() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Cine(), new Cine()));
        List<Cine> resultado = cineService.listarCines();
        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGuardarCine() {
        Cine cine = new Cine();
        cine.setNombre("Cine Hoyts");
        when(repository.save(any(Cine.class))).thenReturn(cine);

        Cine guardado = cineService.guardarCine(cine);

        assertNotNull(guardado);
        assertEquals("Cine Hoyts", guardado.getNombre());
        verify(repository, times(1)).save(cine);
    }

    @Test
    public void testBuscarPorId() {
        Integer id = 1;
        Cine cine = new Cine();
        cine.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(cine));

        Cine resultado = cineService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testBorrarCine() {
        Integer id = 1;
        cineService.borrarCine(id);
        verify(repository, times(1)).deleteById(id);
    }

}
