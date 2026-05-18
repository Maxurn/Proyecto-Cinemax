package com.cine.ms_peliculas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cine.ms_peliculas.Model.Pelicula;
import com.cine.ms_peliculas.Service.PeliculaService;

@RestController
@RequestMapping("/api/v1/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService service;

    //LISTAR TODAS LAS PELIS
    @GetMapping
    public ResponseEntity<List<Pelicula>> listarTodoC() {
        List<Pelicula> lista = service.listarTodo();
        return ResponseEntity.ok(lista);
    }

    //LISTAR UNA PELI POR ID
    @GetMapping("/{id}")
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
    public ResponseEntity<Pelicula> guardarCont(@RequestBody Pelicula Pelicula) {
        Pelicula nuevaPelicula = service.guardar(Pelicula);
        return new ResponseEntity<Pelicula>(nuevaPelicula, HttpStatus.CREATED); 
    }

    //BORRAR PELI
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCont(@PathVariable Integer id) {
        service.borrar(id);
        return ResponseEntity.noContent().build();
    }
    //ACTUALIZAR INFO PELI
    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarCont(@PathVariable Integer id, @RequestBody Pelicula peliAct) {
        Pelicula peliActualizada = service.actualizarPelicula(id, peliAct);
        return ResponseEntity.ok(peliActualizada);
    }

    //FILTRAR POR RATING EDAD (ATP, +18, +16)
    @GetMapping("/filtraRating/{rating}")
    public ResponseEntity<List<Pelicula>> listarPorRatingC(@PathVariable String rating) {
        List<Pelicula> listaFiltrada = service.listarPorRating(rating);
        return ResponseEntity.ok(listaFiltrada);
    }
}
