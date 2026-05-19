package com.cine.ms_sucursal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cine.ms_sucursal.Model.Cine;
import com.cine.ms_sucursal.Model.Sala;
import com.cine.ms_sucursal.Service.CineService;
import com.cine.ms_sucursal.Service.SalaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cines")
public class CineController {

    @Autowired
    private CineService cineService;

    @Autowired
    private SalaService salaService;

    // LISTAR CINES
    @GetMapping
    public ResponseEntity<List<Cine>> listarCinesCont() {
        return ResponseEntity.ok(cineService.listarCines());
    }
    // BUSCAR CINE POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Cine> buscarPorIdCont(@PathVariable Integer id) {
        Cine cine = cineService.buscarPorId(id);
        
        if (cine == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(cine);
    }

    // FILTRAR CINES POR CIUDAD
    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<Cine>> listarPorCiudadCont(@PathVariable String ciudad) {
        List<Cine> cines = cineService.listarPorCiudad(ciudad);
        return ResponseEntity.ok(cines);
    }

    // GUARDAR CINE
    @PostMapping
    public ResponseEntity<Cine> guardarCont(@RequestBody Cine cine) {
        return new ResponseEntity<Cine>(cineService.guardarCine(cine), HttpStatus.CREATED);
    }

    // BORRAR CINE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCine(@PathVariable Integer id) {
        Cine cine = cineService.buscarPorId(id);
        
        if (cine == null) {
            return ResponseEntity.notFound().build(); 
        }
        
        cineService.borrarCine(id);
        return ResponseEntity.noContent().build();
    }

    // AGREGAR SALA A UN CINE
    @PostMapping("/{cineId}/salas")
    public ResponseEntity<Sala> agregarSala(@PathVariable Integer cineId, @RequestBody Sala sala) {
        Sala nuevaSala = salaService.agregarSalaACine(cineId, sala);
        
        if (nuevaSala == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Sala>(nuevaSala, HttpStatus.CREATED);
    }

    // ACTUALIZAR INFO CINE
    @PutMapping("/{id}")
    public ResponseEntity<Cine> actualizarCont(@PathVariable Integer id, @RequestBody Cine cineDetalles) {
    Cine cineActualizado = cineService.actualizarCine(id, cineDetalles);
    
    if (cineActualizado != null) {
        return ResponseEntity.ok(cineActualizado); 
    }
    return ResponseEntity.notFound().build(); 
    }
}