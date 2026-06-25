package com.cine.ms_peliculas.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.cine.ms_peliculas.Model.Pelicula;
import com.cine.ms_peliculas.Repository.PeliculaRepository;


@SpringBootTest
@ActiveProfiles("test")
public class PeliculaServiceTests {

    @MockitoBean
    private PeliculaRepository repository;

    @Autowired
    private PeliculaService peliculaService;

    @Test
    public void testListarTodo(){
        when(repository.findAll()).thenReturn(List.of(new Pelicula())); 

        List<Pelicula> resultados = peliculaService.listarTodo();

        assertNotNull(resultados);
        assertEquals(1, resultados.size());
    }

    @Test
    public void testGuardar() {
        Pelicula peli = new Pelicula();
        when(repository.save(any(Pelicula.class))).thenReturn(peli);

        Pelicula resultado = peliculaService.guardar(peli);

        assertNotNull(resultado);
        verify(repository, times(1)).save(peli);
    }

    @Test
    public void testBorrarPeliculaExistente() {
        Integer id = 1;
        Pelicula peli = new Pelicula();
        when(repository.findById(id)).thenReturn(Optional.of(peli));

        peliculaService.borrar(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testBorrarPeliculaSiNoExiste() {
        Integer id = 99;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> peliculaService.borrar(id));
    }

}
