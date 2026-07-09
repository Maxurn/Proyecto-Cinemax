package com.cine.ms_peliculas.Assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.cine.ms_peliculas.Controller.PeliculaControllerV2;
import com.cine.ms_peliculas.Model.Pelicula;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PeliculaModelAssembler implements RepresentationModelAssembler<Pelicula, EntityModel<Pelicula>> {

    @Override
    public EntityModel<Pelicula> toModel(Pelicula pelicula) {
        return EntityModel.of(pelicula,
            //CONEXION PARA INGRESAR A ESA PROPIA PELI
                linkTo(methodOn(PeliculaControllerV2.class).getPeliculaById(pelicula.getId())).withRel("ingresar-a-id-pelicula"),
            //VOLVER AL LISTADO COMPLETO
                linkTo(methodOn(PeliculaControllerV2.class).getAllPeliculas()).withRel("todas-las-peliculas"),
            //PARA VER LAS PELICULAS CON EL MISMO RATING
                linkTo(methodOn(PeliculaControllerV2.class).getPeliculasByRating(pelicula.getRatingEdad())).withRel("peliculas-con-el-mismo-rating"));
    }
}