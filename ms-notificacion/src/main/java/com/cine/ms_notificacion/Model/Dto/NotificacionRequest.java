package com.cine.ms_notificacion.Model.Dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Datos necesarios para enviar una notificacion")
public class NotificacionRequest {

@Schema(description = "Correo del destinatario", example = "juan@cine.cl")
private String correoDestino;

@Schema(description = "ID de la funcion reservada", example = "5")
private Integer funcionId;

@Schema(description = "Codigo del ticket generado", example = "TKT-1718652345678")
private String codigoTicket;
}
