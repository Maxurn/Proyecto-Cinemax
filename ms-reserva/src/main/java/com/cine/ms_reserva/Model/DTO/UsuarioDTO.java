package com.cine.ms_reserva.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Integer id;
    private String  nombre;
    private String  segundoNombre;
    private String  apellido;
    private String  correo;
    private String  telefono;
    private String  rut;
}
