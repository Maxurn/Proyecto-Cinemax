package com.cine.ms_usuario.controller;

import com.cine.ms_usuario.model.Usuario;
import com.cine.ms_usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Usuarios", description = "Gestion de usuarios del cine")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    @Operation (summary= "Registrar usuario", description = "Crear nuevo usuario en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode= "201", description = "Usuario creado con exito"),
        @ApiResponse(responseCode = "400", description = "rut o correo ya registrado")
    })
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        
        if(usuarioService.registrar(usuario) != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrar(usuario));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Operation(summary = "Listar usuarios", description ="Lista todos los usuarios registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida con exito")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary= "Obtener un usuario por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "usuario no encontrado")

    })
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable int id) {

        Usuario usuario = usuarioService.obtenerPorId(id);
        if(usuario != null){
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description= "Actualiza los datos del usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<Usuario> actualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable int id) {

        if(usuarioService.eliminar(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
