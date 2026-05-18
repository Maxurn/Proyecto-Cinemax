package com.cine.ms_butacas.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "butaca")
@AllArgsConstructor
@NoArgsConstructor
public class Butaca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "funcion_id", nullable = false)
    private Integer funcionId;   
    @Column(name = "sala_id", nullable = false)
    private Integer salaId;      

    @Column(nullable = false)
    private Integer numero;      

    @Column(nullable = false)
    private String status;   //FREE, LOCKED, BOOKED

    @Column(name = "locked_until")
    private LocalDateTime lockedUntil; 
}
