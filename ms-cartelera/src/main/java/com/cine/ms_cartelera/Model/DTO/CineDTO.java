package com.cine.ms_cartelera.Model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema (description = "Entidad que trae datos desde cine (ms-sucursal)")
public class CineDTO {

    @Schema (description = "Nombre del cine", example = "Cine mark")
    private String nombre;
    @Schema (description = "Ubicacion del cine", example = "Salesianos 4400")
    private String direccion;
}
