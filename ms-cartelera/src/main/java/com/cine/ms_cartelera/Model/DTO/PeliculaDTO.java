package com.cine.ms_cartelera.Model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema (description = "Entidad que trae datos desde pelicula (ms-pelicula)")
public class PeliculaDTO {

    @Schema (description = "Id de la pelicula", example = "1")
    private Integer id;
    @Schema (description = "Nombre de la cinta", example = "Los tres mosqueteros")
    private String tituloCinta;
    @Schema (description = "Rating de la pelicula", example = "+18")
    private String ratingEdad;
}
