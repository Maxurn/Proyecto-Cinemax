package com.cine.ms_cartelera.Model.DTO;
import lombok.Data;

@Data
public class SalaDTO {

    private Integer id;
    private String nombre;
    private Integer capacidad;
    private CineDTO cine;
}
