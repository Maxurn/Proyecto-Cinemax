package com.cine.ms_cartelera.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cine.ms_cartelera.Model.Funcion;
import com.cine.ms_cartelera.Model.DTO.FuncionDetalleDTO;
import com.cine.ms_cartelera.Service.FuncionService;

@RestController
@RequestMapping("/api/v1/funciones")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;
    //

    @GetMapping("/{id}")
    public ResponseEntity<FuncionDetalleDTO> obtenerDetalleController(@PathVariable Integer id){
        FuncionDetalleDTO detalle = funcionService.obtenerDetalle(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build(); 
        }else{
            return ResponseEntity.ok(detalle);
        }
    }

    @GetMapping()
    public ResponseEntity<List<FuncionDetalleDTO>> ListarFuncionesC(){
        return ResponseEntity.ok(funcionService.ListarFunciones());
    }

    @GetMapping("/pelicula/{peliculaId}")
    public ResponseEntity<List<FuncionDetalleDTO>> listarPorPeliculaC(@PathVariable Integer peliculaId) {
        return ResponseEntity.ok(funcionService.listarPorPelicula(peliculaId));
    }

    @PostMapping
    public ResponseEntity<Funcion> guardarC(@RequestBody Funcion funcion) {
        Funcion nueva = funcionService.nuevaFuncion(funcion);
        return new ResponseEntity<Funcion>(nueva, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Funcion> actualizarC(@PathVariable Integer id, @RequestBody Funcion detalles) {
        Funcion funcionAct = funcionService.actualizarFuncion(id, detalles);
        return ResponseEntity.ok(funcionAct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFuncC(@PathVariable Integer id) {
        funcionService.eliminarFuncion(id);
        return ResponseEntity.noContent().build();
    }
}
