package com.cine.ms_confiteria.Model.DTO;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema (description = "Entidad que junta datos y se utiliza al registrar/traer datos en ms-pago")
public class PagoDTO {

    @Schema (description = "Id del pago", example = "1")
    private Integer id;
    @Schema (description = "Id del Combo", example = "3")
    private Integer comboId;
    @Schema (description = "Monto total del pago", example = "10990")
    private Double monto;
    @Schema (description = "Metodo del pago", example = "CREDITO")
    private String metodoPago;
    @Schema (description = "Estado del pago efectuado", example = "APROBADO")
    private String estado;
    @Schema (description = "Fecha del registro del pago", example = "20/09/2027")
    private LocalDate fecha;

}
