package com.cine.ms_pago.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoReservaDTO {

    private Integer reservaId;
    private Double monto;
    private String metodoPago;
}
