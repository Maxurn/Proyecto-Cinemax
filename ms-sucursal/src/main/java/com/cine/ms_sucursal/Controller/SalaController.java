package com.cine.ms_sucursal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cine.ms_sucursal.Model.Sala;
import com.cine.ms_sucursal.Service.SalaService;

@RestController
@RequestMapping("/api/v1/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    //LISTAR TODAS LAS SALAS
    @GetMapping
    public ResponseEntity<List<Sala>> listarTodasCont() {
        return ResponseEntity.ok(salaService.listarTodas());
    }
    //BUSCAR POR ID
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
    @GetMapping("/cine/{cineId}")
    public ResponseEntity<List<Sala>> listarPorCineCont(@PathVariable Integer cineId) {
        return ResponseEntity.ok(salaService.listarSalasPorCine(cineId));
    }
    // ELIMINAR SALA
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
    @PutMapping("/{id}")
    public ResponseEntity<Sala> actualizarCont(@PathVariable Integer id, @RequestBody Sala salaDetalles) {
    Sala salaActualizada = salaService.actualizarSala(id, salaDetalles);
    
    if (salaActualizada != null) {
        return ResponseEntity.ok(salaActualizada); 
    }
    return ResponseEntity.notFound().build(); 
    }
}