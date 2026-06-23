package com.cine.ms_sucursal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cine.ms_sucursal.Model.Sala;
import com.cine.ms_sucursal.Service.SalaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Salas", description = "Metodos relacionados con el catalogo de sala.")
@RequestMapping("/api/v1/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    //LISTAR TODAS LAS SALAS
    @Operation (summary = "Obtener todas las Salas", description = "Retorna en una lista todas las salas disponibles.")
    @ApiResponse (responseCode = "200", description = "Exito")
    @GetMapping
    public ResponseEntity<List<Sala>> listarTodasCont() {
        return ResponseEntity.ok(salaService.listarTodas());
    }
    //BUSCAR POR ID
    @Operation (summary = "Obtener sala por ID", description = "Retorna una sala en base al ID dado.")
    @ApiResponse (responseCode = "200", description = "Encontrado.")
    @ApiResponse (responseCode = "404", description = "NO encontrado.")
    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorIdC(@PathVariable Integer id){
        Sala sala = salaService.buscarPorId(id);
        if (sala == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(sala);
        }
    }
    //SALAS POR CINE
    @Operation (summary = "Obtener sala por su Cine", description = "Retorna una sala en base al cineId conectado.")
    @ApiResponse (responseCode = "200", description = "Exito")
    @GetMapping("/cine/{cineId}")
    public ResponseEntity<List<Sala>> listarPorCineCont(@PathVariable Integer cineId) {
        return ResponseEntity.ok(salaService.listarSalasPorCine(cineId));
    }
    // ELIMINAR SALA
    @Operation (summary = "Eliminar una sala", description = "Elimina una sala segun su ID.")
    @ApiResponse (responseCode = "200", description = "Eliminada.")
    @ApiResponse (responseCode = "404", description = "NO se pudo borrar.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Sala sala = salaService.buscarPorId(id);
        
        if (sala == null) {
            return ResponseEntity.notFound().build();
        }
        
        salaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    // ACTUALIZAR SALA
    @Operation (summary = "Actualizar datos de una sala", description = "Actualiza los datos de una sala segun su ID.")
    @ApiResponse (responseCode = "200", description = "Actualizada.")
    @ApiResponse (responseCode = "404", description = "NO se encontro.")
    @PutMapping("/{id}")
    public ResponseEntity<Sala> actualizarCont(@PathVariable Integer id, @RequestBody Sala salaDetalles) {
    Sala salaActualizada = salaService.actualizarSala(id, salaDetalles);
    
    if (salaActualizada != null) {
        return ResponseEntity.ok(salaActualizada); 
    }
    return ResponseEntity.notFound().build(); 
    }
}