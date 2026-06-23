package com.cine.ms_pago.Model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema (description = "Entidad proveniente del microservicio CONFITERIA")
public class PagoConfiteriaDTO {

    @Schema (description = "Id del combo", example = "2")
    private Integer comboId;
    @Schema (description = "Monto de la transaccion", example = "9990")
    private Double monto;
    @Schema (description = "Metodo de pago usado", example = "Credito/Debito")
    private String metodoPago;
}
