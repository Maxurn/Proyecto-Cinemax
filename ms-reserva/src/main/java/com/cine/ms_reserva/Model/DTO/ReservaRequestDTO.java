package com.cine.ms_reserva.Model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos necesarios para crear una reserva")
public class ReservaRequestDTO {

    @Schema(description = "ID del usuario", example = "3")
    private Integer usuarioId;

    @Schema(description = "ID de la funcion", example = "5")
    private Integer funcionId;

    @Schema(description = "ID de la butaca", example = "14")
    private Integer butacaId;

    @Schema(description = "Monto a pagar", example = "4500.0")
    private Double  monto;

    @Schema(description = "Metodo de pago: TARJETA o EFECTIVO", example = "TARJETA")
    private String  metodoPago;  // ej: "TARJETA", "EFECTIVO"
}
