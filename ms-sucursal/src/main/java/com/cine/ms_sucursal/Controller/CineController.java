package com.cine.ms_sucursal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cine.ms_sucursal.Model.Cine;
import com.cine.ms_sucursal.Model.Sala;
import com.cine.ms_sucursal.Service.CineService;
import com.cine.ms_sucursal.Service.SalaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@Tag(name = "Cine", description = "Metodos relacionados con el catalogo de cines.")
@RequestMapping("/api/v1/cines")
public class CineController {


    @Autowired
    private CineService cineService;

    @Autowired
    private SalaService salaService;

    // LISTAR CINES
    @Operation(summary = "Listar cines", description = "Retorna la lista completa de cines.")
    @ApiResponse(responseCode = "200", description = "Exito.")
    @GetMapping
    public ResponseEntity<List<Cine>> listarCinesCont() {
        return ResponseEntity.ok(cineService.listarCines());
    }
    // BUSCAR CINE POR ID
    @Operation(summary = "Buscar cine por ID", description = "Busca un cine por el ID dado.")
    @ApiResponse(responseCode = "200", description = "Cine encontrado.")
    @ApiResponse(responseCode = "404", description = "Cine no encontrado.")
    @GetMapping("/{id}")
    public ResponseEntity<Cine> buscarPorIdCont(@PathVariable Integer id) {
        Cine cine = cineService.buscarPorId(id);
        
        if (cine == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(cine);
    }

    // FILTRAR CINES POR CIUDAD
    @Operation(summary = "Filtrar por ciudad (o comuna)", description = "Obtiene cines localizados en una ciudad/comuna específica.")
    @ApiResponse(responseCode = "200", description = "Exito.")
    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<Cine>> listarPorCiudadCont(@PathVariable String ciudad) {
        List<Cine> cines = cineService.listarPorCiudad(ciudad);
        return ResponseEntity.ok(cines);
    }

    // GUARDAR CINE
    @Operation(summary = "Guardar cine", description = "Crea un nuevo registro de cine.")
    @ApiResponse(responseCode = "201", description = "Cine creado.")
    @PostMapping
    public ResponseEntity<Cine> guardarCont(@RequestBody Cine cine) {
        return new ResponseEntity<Cine>(cineService.guardarCine(cine), HttpStatus.CREATED);
    }

    // BORRAR CINE
    @Operation(summary = "Eliminar cine", description = "Elimina un cine y sus salas conectadas.")
    @ApiResponse(responseCode = "204", description = "Cine eliminado.")
    @ApiResponse(responseCode = "404", description = "Cine no encontrado.")
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
    @Operation(summary = "Agregar sala a cine", description = "Vincula una nueva sala a un cineId.")
    @ApiResponse(responseCode = "201", description = "Sala agregada exitosamente.")
    @ApiResponse(responseCode = "404", description = "Cine no encontrado.")
    @PostMapping("/{cineId}/salas")
    public ResponseEntity<Sala> agregarSala(@PathVariable Integer cineId, @RequestBody Sala sala) {
        Sala nuevaSala = salaService.agregarSalaACine(cineId, sala);
        
        if (nuevaSala == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Sala>(nuevaSala, HttpStatus.CREATED);
    }

    // ACTUALIZAR INFO CINE
    @Operation(summary = "Actualizar cine", description = "Actualiza un cine por su ID.")
    @ApiResponse(responseCode = "200", description = "Actualizado.")
    @ApiResponse(responseCode = "404", description = "Cine no encontrado.")
    @PutMapping("/{id}")
    public ResponseEntity<Cine> actualizarCont(@PathVariable Integer id, @RequestBody Cine cineDetalles) {
    Cine cineActualizado = cineService.actualizarCine(id, cineDetalles);
    
    if (cineActualizado != null) {
        return ResponseEntity.ok(cineActualizado); 
    }
    return ResponseEntity.notFound().build(); 
    }
}