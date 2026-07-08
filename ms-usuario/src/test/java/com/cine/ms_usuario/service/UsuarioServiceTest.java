package com.cine.ms_usuario.service;

import com.cine.ms_usuario.model.Usuario;
import com.cine.ms_usuario.repository.UsuarioRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setCorreo("juan@cine.cl");
        usuario.setTelefono("+56912345678");
        usuario.setRut("12345678");
    }

    @Test
    void registrar_conDatosValidos_retornaUsuarioGuardado() {
        when(usuarioRepository.existsByRut("12345678")).thenReturn(false);
        when(usuarioRepository.existsByCorreo("juan@cine.cl")).thenReturn(false);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.registrar(usuario);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void registrar_conRutInvalido_retornaNull() {
        usuario.setRut("123");

        Usuario resultado = usuarioService.registrar(usuario);

        assertNull(resultado);
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void registrar_conRutYaExistente_retornaNull() {
        when(usuarioRepository.existsByRut("12345678")).thenReturn(true);

        Usuario resultado = usuarioService.registrar(usuario);

        assertNull(resultado);
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void registrar_conCorreoYaExistente_retornaNull() {
        when(usuarioRepository.existsByRut("12345678")).thenReturn(false);
        when(usuarioRepository.existsByCorreo("juan@cine.cl")).thenReturn(true);

        Usuario resultado = usuarioService.registrar(usuario);

        assertNull(resultado);
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void listar_retornaListaDeUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));

        List<Usuario> resultado = usuarioService.listar();

        assertEquals(1, resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_conIdExistente_retornaUsuario() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.obtenerPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }

    @Test
    void obtenerPorId_conIdInexistente_retornaNull() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        Usuario resultado = usuarioService.obtenerPorId(99);

        assertNull(resultado);
    }

    @Test
    void actualizar_conDatosNuevos_actualizaCamposNoNulos() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario datosNuevos = new Usuario();
        datosNuevos.setNombre("Carlos");
        datosNuevos.setCorreo("carlos@cine.cl");

        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario resultado = usuarioService.actualizar(1, datosNuevos);

        assertEquals("Carlos", resultado.getNombre());
        assertEquals("carlos@cine.cl", resultado.getCorreo());
        assertEquals("Perez", resultado.getApellido()); // no se modificó
    }

    @Test
    void eliminar_conIdExistente_retornaTrue() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        boolean resultado = usuarioService.eliminar(1);

        assertTrue(resultado);
        verify(usuarioRepository, times(1)).deleteById(1);
    }

    @Test
    void eliminar_conIdInexistente_retornaFalse() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        boolean resultado = usuarioService.eliminar(99);

        assertFalse(resultado);
        verify(usuarioRepository, never()).deleteById(any());
    }
}
