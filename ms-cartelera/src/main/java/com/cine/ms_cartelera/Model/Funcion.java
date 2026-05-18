package com.cine.ms_cartelera.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "funcion")
@AllArgsConstructor
@NoArgsConstructor
public class Funcion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pelicula_id", nullable = false)
    private Integer peliculaId;

    @Column(name = "sala_id", nullable = false)
    private Integer salaId;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    private Double precioEntrada;


}
