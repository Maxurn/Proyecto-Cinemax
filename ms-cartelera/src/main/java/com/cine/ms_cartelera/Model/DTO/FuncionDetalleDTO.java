package com.cine.ms_cartelera.Model.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema (description = "Entidad que une todos los datos para generar el detalle de la funcion")
public class FuncionDetalleDTO {

    @Schema (description = "Id del detalle", example = "1")
    private Integer id;
    @Schema (description = "Fecha de la funcion", example = "28-07-2026")
    private LocalDate fecha;
    @Schema (description = "Hora de la funcion", example = "15:00:00")
    private LocalTime hora;
    @Schema (description = "Valor de la entrada a la funcion", example = "4000.0")
    private Double precioEntrada;

    @Schema (description = "Nombre de la pelicula", example = "Turbo")
    private String nombrePelicula;
    @Schema (description = "Nombre del cine", example = "Cine Max")
    private String nombreCine;
    @Schema (description = "Nombre de la sala", example = "Sala Comun 2")
    private String nombreSala;
}
