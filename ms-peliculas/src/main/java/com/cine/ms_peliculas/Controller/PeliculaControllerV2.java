package com.cine.ms_peliculas.Controller;

import com.cine.ms_peliculas.Assemblers.PeliculaModelAssembler;
import com.cine.ms_peliculas.Model.Pelicula;
import com.cine.ms_peliculas.Service.PeliculaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/peliculas")
@Tag(name = "Películas V2", description = "Operaciones relacionadas con el catálogo de películas, Junto con sistema HATEOAS")
public class PeliculaControllerV2 {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PeliculaModelAssembler assembler;

    @Operation (summary = "Obtener todas las peliculas", description = "Retorna una lista de todas las cintas disponibles.")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Pelicula>> getAllPeliculas() {
        List<EntityModel<Pelicula>> peliculas = peliculaService.listarTodo().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(peliculas,
                linkTo(methodOn(PeliculaControllerV2.class).getAllPeliculas()).withSelfRel());
    }

    @Operation (summary = "Buscar pelicula por su ID", description = "Busca y retorna una pelicula en base a su ID")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Pelicula> getPeliculaById(@PathVariable Integer id) {
        Pelicula pelicula = peliculaService.buscarId(id);
        return assembler.toModel(pelicula);
    }

    @Operation (summary = "Guardar nueva pelicula", description = "Guarda una nueva pelicula en la base de datos")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    @PostMapping(value = "/guardar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Pelicula>> crearPelicula(@RequestBody Pelicula pelicula) {
        Pelicula newPelicula = peliculaService.guardar(pelicula);
        return ResponseEntity
                .created(linkTo(methodOn(PeliculaControllerV2.class).getPeliculaById(newPelicula.getId())).toUri())
                .body(assembler.toModel(newPelicula));
    }

    @Operation (summary = "Actualizar pelicula", description = "Actualiza la informacion de una pelicula en base a su ID")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Pelicula>> actPelicula(@PathVariable Integer id, @RequestBody Pelicula pelicula) {
        Pelicula updatedPelicula = peliculaService.actualizarPelicula(id, pelicula);
        return ResponseEntity.ok(assembler.toModel(updatedPelicula));
    }

    @Operation (summary = "Borrar pelicula", description = "Busca y borra la pelicula seleccionada en base a su ID")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> borrarPeli(@PathVariable Integer id) {
        peliculaService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation (summary = "Filtrar por Rating", description = "Muestra todas las peliculas que tengan el rating dado")
    @ApiResponse (responseCode = "200", description = "Operacion exitosa")
    @GetMapping(value = "/filtraRating/{rating}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Pelicula>> getPeliculasByRating(@PathVariable String rating) {
        List<EntityModel<Pelicula>> peliculas = peliculaService.listarPorRating(rating).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(peliculas,
                linkTo(methodOn(PeliculaControllerV2.class).getPeliculasByRating(rating)).withSelfRel());
    }
}