package com.cine.ms_peliculas.Controller;

import com.cine.ms_peliculas.Assemblers.PeliculaModelAssembler;
import com.cine.ms_peliculas.Model.Pelicula;
import com.cine.ms_peliculas.Service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType; // Importamos el tipo de medio estándar
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/peliculas")
public class PeliculaControllerV2 {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PeliculaModelAssembler assembler;

    // Cambiamos HAL_JSON_VALUE por APPLICATION_JSON_VALUE
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Pelicula>> getAllCarreras() {
        List<EntityModel<Pelicula>> peliculas = peliculaService.listarTodo().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(peliculas,
                linkTo(methodOn(PeliculaControllerV2.class).getAllCarreras()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Pelicula> getCarreraByCodigo(@PathVariable Integer id) {
        Pelicula pelicula = peliculaService.buscarId(id);
        return assembler.toModel(pelicula);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Pelicula>> createCarrera(@RequestBody Pelicula pelicula) {
        Pelicula newPelicula = peliculaService.guardar(pelicula);
        return ResponseEntity
                .created(linkTo(methodOn(PeliculaControllerV2.class).getCarreraByCodigo(newPelicula.getId())).toUri())
                .body(assembler.toModel(newPelicula));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Pelicula>> updateCarrera(@PathVariable Integer id, @RequestBody Pelicula pelicula) {
        Pelicula updatedPelicula = peliculaService.actualizarPelicula(id, pelicula);
        return ResponseEntity.ok(assembler.toModel(updatedPelicula));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCarrera(@PathVariable Integer id) {
        peliculaService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/rating/{rating}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Pelicula>> getPeliculasByRating(@PathVariable String rating) {
        List<EntityModel<Pelicula>> peliculas = peliculaService.listarPorRating(rating).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(peliculas,
                linkTo(methodOn(PeliculaControllerV2.class).getPeliculasByRating(rating)).withSelfRel());
    }
}