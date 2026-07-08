package com.cine.ms_cartelera.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cine.ms_cartelera.Model.Funcion;
import com.cine.ms_cartelera.Model.DTO.FuncionDetalleDTO;
import com.cine.ms_cartelera.Service.FuncionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/funciones")
@Tag(name = "Cartelera", description = "Operaciones relacionadas con la Cartelera")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;
    //

    @Operation(summary = "Obtener detalle de Funcion", description = "Busca una Funcion segun su ID.")
    @ApiResponse(responseCode = "200", description = "Detalle encontrado.")
    @ApiResponse(responseCode = "404", description =  "Funcion no encontrada.")
    @GetMapping("/{id}")
    public ResponseEntity<FuncionDetalleDTO> obtenerDetalleController(@PathVariable Integer id){
        FuncionDetalleDTO detalle = funcionService.obtenerDetalle(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build(); 
        }else{
            return ResponseEntity.ok(detalle);
        }
    }

    @Operation(summary = "Listar todas las funciones", description = "Retorna todas las funciones disponibles")
    @ApiResponse(responseCode = "200", description = "Exito")
    @GetMapping()
    public ResponseEntity<List<FuncionDetalleDTO>> ListarFuncionesC(){
        return ResponseEntity.ok(funcionService.ListarFunciones());
    }

    @Operation(summary = "Listar por película", description = "Filtra las funciones según el idPelicula")
    @ApiResponse(responseCode = "200", description = "Funciones encontradas.")
    @GetMapping("/pelicula/{peliculaId}")
    public ResponseEntity<List<FuncionDetalleDTO>> listarPorPeliculaC(@PathVariable Integer peliculaId) {
        return ResponseEntity.ok(funcionService.listarPorPelicula(peliculaId));
    }

    @Operation(summary = "Crear funcion", description = "Crea una nueva Funcion")
    @ApiResponse(responseCode = "201", description =  "Funcion creada.")
    @PostMapping
    public ResponseEntity<Funcion> guardarC(@RequestBody Funcion funcion) {
        Funcion nueva = funcionService.nuevaFuncion(funcion);
        return new ResponseEntity<Funcion>(nueva, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar funcion", description = "Modifica los datos de una Funcion existente.")
    @ApiResponse(responseCode = "200", description = "Funcion actualizada.")
    @PutMapping("/{id}")
    public ResponseEntity<Funcion> actualizarC(@PathVariable Integer id, @RequestBody Funcion detalles) {
        Funcion funcionAct = funcionService.actualizarFuncion(id, detalles);
        return ResponseEntity.ok(funcionAct);
    }

    @Operation(summary = "Eliminar Funcion", description = "Elimina una Funcion segun su ID.")
    @ApiResponse(responseCode = "204", description = "Funcion eliminada.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFuncC(@PathVariable Integer id) {
        funcionService.eliminarFuncion(id);
        return ResponseEntity.noContent().build();
    }
}
