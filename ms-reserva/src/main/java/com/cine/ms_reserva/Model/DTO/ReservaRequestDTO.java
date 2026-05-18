package com.cine.ms_reserva.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequestDTO {

    private Integer usuarioId;
    private Integer funcionId;
    private Integer butacaId;
    private Double  monto;
    private String  metodoPago;  // ej: "TARJETA", "EFECTIVO"
}
