package com.cine.ms_confiteria.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "combo")
@AllArgsConstructor
@NoArgsConstructor
public class Combo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false, unique = true)
    private String nombre;

    @Column (nullable = false)
    private Double valor;
    
    @Column(nullable = false)
    private Integer stock;

    private String categoria;
}
