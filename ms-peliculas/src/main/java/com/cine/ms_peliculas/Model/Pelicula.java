package com.cine.ms_peliculas.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "peliculas")
@Schema (description = "Entidad que representa una pelicula")
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Schema (description = "ID de la pelicula (Autoincrementable)", example = "1")
    private Integer id;

    @Column(name = "titulo_cinta", nullable = false, unique = true)
    @Schema (description = "Nombre de la pelicula", example = "Shrek")
    private String tituloCinta;

    @Column(name = "rating_edad", nullable = false)
    @Schema (description = "Rating de edad de la pelicula", example = "ATP")
    private String ratingEdad;

    @Column(nullable = false)
    @Schema (description = "Longitud en minutos de la pelicula", example = "121")
    private Integer duracion;

}