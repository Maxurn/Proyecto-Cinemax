package com.cine.ms_pago.Model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "pagos")
@AllArgsConstructor
@NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = true)
    private Integer reservaId;

    @Column (nullable = true)
    private Integer comboId;

    @Column (nullable = false)
    private Double monto;

    private String estado;
    private String metodoPago;
    private LocalDate fecha;
}
