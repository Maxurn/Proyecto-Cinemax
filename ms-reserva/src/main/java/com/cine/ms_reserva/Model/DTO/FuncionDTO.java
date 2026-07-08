package com.cine.ms_reserva.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionDTO {

    private Integer   id;
    private Integer   peliculaId;
    private Integer   salaId;
    private LocalDate fecha;
    private LocalTime hora;
    private Double    precioEntrada;
}
