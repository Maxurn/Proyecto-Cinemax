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
                linkTo(methodOn(PeliculaControllerV2.class).getCarreraByCodigo(pelicula.getId())).withSelfRel(),
                linkTo(methodOn(PeliculaControllerV2.class).getAllCarreras()).withRel("peliculas"));
    }
}