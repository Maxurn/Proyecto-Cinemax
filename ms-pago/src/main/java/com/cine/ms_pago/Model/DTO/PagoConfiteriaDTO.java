package com.cine.ms_pago.Model.DTO;

import lombok.Data;

@Data
public class PagoConfiteriaDTO {
    private Integer comboId;
    private Double monto;
    private String metodoPago;
}
