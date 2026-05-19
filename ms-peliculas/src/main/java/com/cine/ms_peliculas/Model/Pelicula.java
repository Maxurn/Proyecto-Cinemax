package com.cine.ms_peliculas.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "peliculas")
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo_cinta", nullable = false, unique = true)
    private String tituloCinta;

    @Column(name = "rating_edad", nullable = false)
    private String ratingEdad;

    @Column(nullable = false)
    private Integer duracion;

}