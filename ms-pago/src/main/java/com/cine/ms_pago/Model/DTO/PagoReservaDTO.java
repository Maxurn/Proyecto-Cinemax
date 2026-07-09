package com.cine.ms_pago.Model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Schema (description = "Entidad proveniente del microservicio RESERVA")
@NoArgsConstructor
public class PagoReservaDTO {

    @Schema (description = "Id de la reserva", example = "1")
    private Integer reservaId;

    @Schema (description = "Monto de la transaccion", example = "9990")
    private Double monto;

    @Schema (description = "Metodo de pago usado", example = "Credito/Debito")
    private String metodoPago;
}
