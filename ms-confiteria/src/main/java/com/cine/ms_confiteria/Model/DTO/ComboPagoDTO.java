package com.cine.ms_confiteria.Model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema (description = "Entidad que junta datos y se utiliza al registrar/traer datos en ms-pago")
@AllArgsConstructor
@NoArgsConstructor
public class ComboPagoDTO {

    @Schema (description = "Id del combo", example = "2")
    private Integer comboId;

    @Schema (description = "Monto del combo", example = "19900")
    private Double monto;
    
    @Schema (description = "Metodo de pago del combo", example = "CREDITO")
    private String metodoPago;

}
