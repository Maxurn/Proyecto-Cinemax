package com.cine.ms_sucursal.Model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "cine")
@Schema(description = "Entidad que representa un Cine")
@AllArgsConstructor
@NoArgsConstructor
public class Cine {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Schema(description = "Id Autoincrementable del cine", example = "1")
    private Integer id;

    @Column (nullable = false, unique= true)
    @Schema(description = "Nombre de cine", example = "Cinemax Lo Valledor")
    private String nombre;

    @Schema(description = "Direccion del cine", example = "Av. Departamental 1222")
    private String direccion;

    @Schema(description = "Ciudad/Comuna del cine", example = "San Miguel")
    private String ciudad;

    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL)
    @Schema(description = "Lista de salas conectadas al cine", example = "2")
    @JsonIgnore
    private List<Sala> salas;

}
