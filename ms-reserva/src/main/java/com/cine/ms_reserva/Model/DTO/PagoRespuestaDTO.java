package com.cine.ms_reserva.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRespuestaDTO {

    private Integer   id;
    private Integer   reservaId;
    private Double    monto;
    private String    metodoPago;
    private String    estado;      // APROBADO | RECHAZADO | PENDIENTE
    private LocalDate fecha;
}
