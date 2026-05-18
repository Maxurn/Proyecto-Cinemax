package com.cine.ms_notificacion.Model.Dto;
import lombok.Data;

@Data
public class NotificacionRequest {
private String correoDestino;
private Integer funcionId;
private String codigoTicket;
}
