package com.cine.ms_confiteria.Model.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PagoDTO {

    private Integer id;
    private Integer comboId;
    private Double monto;
    private String metodoPago;
    private String estado;
    private LocalDate fecha;

}
