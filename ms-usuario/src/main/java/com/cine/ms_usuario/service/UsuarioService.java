package com.cine.ms_usuario.service;

import com.cine.ms_usuario.model.Usuario;
import com.cine.ms_usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@RequiredArgsConstructor
public class UsuarioService {   

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;

    public Usuario registrar(Usuario usuario) {
        
        if(usuario.getRut().length() != 8){
            log.warn("El RUT debe tener exactamente 8 dígitos: {}", usuario.getRut());
            return null;
        }
        if(usuarioRepository.existsByRut(usuario.getRut())){
            log.warn("El RUT ya está registrado: {}", usuario.getRut());
            return null;
        }
        if(usuarioRepository.existsByCorreo(usuario.getCorreo())){
            log.warn("El correo ya está registrado: {}", usuario.getCorreo());
            return null;
        }

        log.info("Registrando nuevo usuario con RUT {}", usuario.getRut());
        return usuarioRepository.save(usuario);
        
    }

    public List<Usuario> listar() {
        log.info("Listando todos los usuarios");
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(int id) {
        
        log.info("Buscando usuario con id {}", id);
        return usuarioRepository.findById(id).orElse(null);

    }

    public Usuario actualizar(int id, Usuario datos) {
        Usuario usuario = obtenerPorId(id);

        if (datos.getNombre() != null){
            usuario.setNombre(datos.getNombre());
        }
        if (datos.getSegundoNombre() != null){
            usuario.setSegundoNombre(datos.getSegundoNombre());
        }
        if (datos.getApellido() != null){
            usuario.setApellido(datos.getApellido());
        }
        if (datos.getCorreo() != null){
            usuario.setCorreo(datos.getCorreo());
        }
        if (datos.getTelefono() != null){
            usuario.setTelefono(datos.getTelefono());
        }

        log.info("Usuario {} actualizado", id);
        return usuarioRepository.save(usuario);
    }

    public boolean eliminar(int id) {
        
        if(obtenerPorId(id) != null){
            usuarioRepository.deleteById(id);
            log.info("Usuario {} eliminado", id);
            return true;
        }
        
        log.warn("No se pudo eliminar, usuario {} no encontrado", id);
        return false;

    }
}
