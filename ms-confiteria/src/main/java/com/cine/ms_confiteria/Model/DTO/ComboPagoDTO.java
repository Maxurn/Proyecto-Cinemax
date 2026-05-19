package com.cine.ms_confiteria.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboPagoDTO {

    private Integer comboId;
    private Double monto;
    private String metodoPago;

}
