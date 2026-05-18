package com.cine.ms_usuario.service;

import com.cine.ms_usuario.model.Usuario;
import com.cine.ms_usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario registrar(Usuario usuario) {
        
        if(usuario.getRut().length() != 8){
            System.out.println("El RUT debe tener exactamente 8 dígitos");
            return null;
        }
        if(usuarioRepository.existsByRut(usuario.getRut())){
            System.out.println("El RUT ya está registrado");
            return null;
        }
        if(usuarioRepository.existsByCorreo(usuario.getCorreo())){
            System.out.println("El correo ya está registrado");
            return null;
        }

        return usuarioRepository.save(usuario);
        
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(int id) {
        
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

        return usuarioRepository.save(usuario);
    }

    public boolean eliminar(int id) {
        
        if(obtenerPorId(id) != null){
            usuarioRepository.deleteById(id);
            return true;
        }

        return false;

    }
}
