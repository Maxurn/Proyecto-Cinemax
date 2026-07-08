package com.cine.ms_sucursal.Model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "sala")
@Schema(description = "Entidad que representa una Sala.")
@AllArgsConstructor
@NoArgsConstructor
public class Sala {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Schema(description = "Id autoincrementable de la sala", example = "1")
    private Integer id;

    @Column (nullable = false)
    @Schema(description = "Nombre de la sala", example = "Sala comun 1")
    private String nombre;

    @Column (nullable = false)
    @Schema(description = "Capacidad de la sala", example = "50")
    private Integer capacidad;

    @ManyToOne
    @JoinColumn(name = "cine_id", nullable = false)
    @Schema(description = "Cine conectado a la sala", example = "2")
    private Cine cine;
}
