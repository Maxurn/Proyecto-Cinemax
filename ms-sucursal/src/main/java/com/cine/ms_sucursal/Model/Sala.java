package com.cine.ms_sucursal.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "sala")
@AllArgsConstructor
@NoArgsConstructor
public class Sala {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private String nombre;

    @Column (nullable = false)
    private Integer capacidad;

    @ManyToOne
    @JoinColumn(name = "cine_id", nullable = false)
    private Cine cine;
}
