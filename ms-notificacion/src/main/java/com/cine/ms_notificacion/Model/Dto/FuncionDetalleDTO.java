package com.cine.ms_notificacion.Model.Dto;
import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Detalle de una funcion obtenido desde ms-cartelera")
public class FuncionDetalleDTO {

    @Schema(description = "ID de la funcion", example = "5")
    private Integer id;

    @Schema(description = "Fecha de la funcion", example = "2026-06-20")
    private LocalDate fecha;

    @Schema(description = "Hora de la funcion", example = "20:30")
    private LocalTime hora;

    @Schema(description = "Precio de la entrada", example = "4500.0")
    private Double precioEntrada;

    @Schema(description = "Nombre de la pelicula", example = "Avengers")
    private String nombrePelicula;

    @Schema(description = "Nombre del cine", example = "Cine Hoyts")
    private String nombreCine;

    @Schema(description = "Nombre de la sala", example = "Sala 3")
    private String nombreSala;

}
