package com.cine.ms_butacas.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Table(name = "butaca")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una butaca de una funcion")
public class Butaca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado", example = "1")
    private Integer id;

    @Column(name = "funcion_id", nullable = false)
     @Schema(description = "ID de la funcion asociada", example = "5")
    private Integer funcionId;   


    @Column(name = "sala_id", nullable = false)
    @Schema(description = "ID de la sala", example = "2")
    private Integer salaId;      

    @Column(nullable = false)
    @Schema(description = "Numero de la butaca", example = "14")
    private Integer numero;      

    @Column(nullable = false)
     @Schema(description = "Estado de la butaca: FREE, LOCKED o BOOKED", example = "FREE")
    private String status; 

    @Column(name = "locked_until")
    @Schema(description = "Fecha y hora hasta cuando esta bloqueada", example = "2026-06-20T21:00:00")
    private LocalDateTime lockedUntil; 
}
