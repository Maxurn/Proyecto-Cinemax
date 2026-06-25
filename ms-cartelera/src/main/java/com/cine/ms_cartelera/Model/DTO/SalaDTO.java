package com.cine.ms_cartelera.Model.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema (description = "Entidad que trae datos desde sala (ms-sucursal)")
public class SalaDTO {

    @Schema (description = "Id de la sala", example = "1")
    private Integer id;
    @Schema (description = "Nombre de la sala", example = "Sala 3DX 1")
    private String nombre;
    @Schema (description = "Capacidad de la sala", example = "35")
    private Integer capacidad;
    @Schema (description = "Cine al que pertenece la sala")
    private CineDTO cine;
}
