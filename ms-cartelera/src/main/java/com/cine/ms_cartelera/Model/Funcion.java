package com.cine.ms_cartelera.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "funcion")
@Schema (description = "Entidad que representa una funcion")
@AllArgsConstructor
@NoArgsConstructor
public class Funcion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Schema (description = "ID de la funcion (Autoincrementable)", example = "1")
    private Integer id;

    @Column(name = "pelicula_id", nullable = false)
    @Schema (description = "ID de la pelicula (Feign)", example = "2")
    private Integer peliculaId;

    @Column(name = "sala_id", nullable = false)
    @Schema (description = "ID de la sala (Feign trae la sala y su cine)", example = "3")
    private Integer salaId;

    @Column(nullable = false)
    @Schema (description = "Fecha de la funcion", example = "26-07-2026")
    private LocalDate fecha;

    @Column(nullable = false)
    @Schema (description = "Hora de la funcion", example = "17:50:00")
    private LocalTime hora;

    @Schema (description = "Precio de la entrada a la funcion", example = "6000.0")
    private Double precioEntrada;


}
