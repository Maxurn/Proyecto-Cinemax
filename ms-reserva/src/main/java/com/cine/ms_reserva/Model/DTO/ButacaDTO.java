package com.cine.ms_reserva.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButacaDTO {

    private Integer id;
    private Integer funcionId;
    private Integer salaId;
    private Integer numero;
    private String  status;
}
