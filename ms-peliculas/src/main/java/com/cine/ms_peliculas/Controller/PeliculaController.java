package com.cine.ms_peliculas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cine.ms_peliculas.Model.Pelicula;
import com.cine.ms_peliculas.Service.PeliculaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/peliculas")
@Tag(name = "Películas", description = "Operaciones relacionadas con el catálogo de películas")
public class PeliculaController {

    @Autowired
    private PeliculaService service;

    //LISTAR TODAS LAS PELIS
    @GetMapping
    @Operation (summary = "Obtener todas las peliculas", description = "Retorna una lista de todas las cintas disponibles.")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    public ResponseEntity<List<Pelicula>> listarTodoC() {
        List<Pelicula> lista = service.listarTodo();
        return ResponseEntity.ok(lista);
    }

    //LISTAR UNA PELI POR ID
    @GetMapping("/{id}")
    @Operation (summary = "Buscar pelicula por su ID", description = "Busca y retorna una pelicula en base a su ID")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    public ResponseEntity<Pelicula> buscarPorIdC(@PathVariable Integer id) {
        Pelicula peli = service.buscarId(id);
        if (peli == null){
            return ResponseEntity.notFound().build();
        }else{
        return ResponseEntity.ok(peli);
        }
    }

    //GUARDAR PELI EN LA BB.DD.
    @PostMapping("/guardar")
    @Operation (summary = "Guardar nueva pelicula", description = "Guarda una nueva pelicula en la base de datos")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    public ResponseEntity<Pelicula> guardarCont(@RequestBody Pelicula Pelicula) {
        Pelicula nuevaPelicula = service.guardar(Pelicula);
        return new ResponseEntity<Pelicula>(nuevaPelicula, HttpStatus.CREATED); 
    }

    //BORRAR PELI
    @DeleteMapping("/{id}")
    @Operation (summary = "Borrar pelicula", description = "Busca y borra la pelicula seleccionada en base a su ID")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    public ResponseEntity<Void> eliminarCont(@PathVariable Integer id) {
        service.borrar(id);
        return ResponseEntity.noContent().build();
    }
    //ACTUALIZAR INFO PELI
    @PutMapping("/{id}")
    @Operation (summary = "Actualizar pelicula", description = "Actualiza la informacion de una pelicula en base a su ID")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    public ResponseEntity<Pelicula> actualizarCont(@PathVariable Integer id, @RequestBody Pelicula peliAct) {
        Pelicula peliActualizada = service.actualizarPelicula(id, peliAct);
        return ResponseEntity.ok(peliActualizada);
    }

    //FILTRAR POR RATING EDAD (ATP, +18, +16)
    @GetMapping("/filtraRating/{rating}")
    @Operation (summary = "Filtrar por Rating", description = "Muestra todas las peliculas que tengan el rating dado")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    public ResponseEntity<List<Pelicula>> listarPorRatingC(@PathVariable String rating) {
        List<Pelicula> listaFiltrada = service.listarPorRating(rating);
        return ResponseEntity.ok(listaFiltrada);
    }
}
