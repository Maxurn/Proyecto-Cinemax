package com.cine.ms_notificacion.Model.Dto;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class FuncionDetalleDTO {
    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double precioEntrada;

    private String nombrePelicula;
    private String nombreCine;
    private String nombreSala;

}
