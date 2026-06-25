package com.cine.ms_confiteria.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "combo")
@Schema (description = "Entidad que representa un Combo o Alimento")
@AllArgsConstructor
@NoArgsConstructor
public class Combo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Schema (description = "ID del combo (Autoincrementable)", example = "1")
    private Integer id;

    @Column (nullable = false, unique = true)
    @Schema (description = "Nombre del combo", example = "Cabritas grandes")
    private String nombre;

    @Column (nullable = false)
    @Schema (description = "Precio del combo", example = "7990")
    private Double valor;
    
    @Column(nullable = false)
    @Schema (description = "Stock disponible del combo", example = "15")
    private Integer stock;

    @Schema (description = "Categoria del combo", example = "Bebidas")
    private String categoria;
}
