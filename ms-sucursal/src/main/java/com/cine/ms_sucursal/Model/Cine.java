package com.cine.ms_sucursal.Model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "cine")
@AllArgsConstructor
@NoArgsConstructor
public class Cine {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false, unique= true)
    private String nombre;

    private String direccion;
    private String ciudad;

    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Sala> salas;

}
