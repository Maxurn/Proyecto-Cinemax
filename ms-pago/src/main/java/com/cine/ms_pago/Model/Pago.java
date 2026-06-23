package com.cine.ms_pago.Model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "pagos")
@Schema(description = "Entidad que representa el pago")
@AllArgsConstructor
@NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Schema (description = "ID de la pelicula (Autoincrementable)", example = "1")
    private Integer id;
    
    @Column(nullable = true)
    @Schema (description = "ID de la reserva", example = "1")
    private Integer reservaId;

    @Column (nullable = true)
    @Schema (description = "ID del combo", example = "2")
    private Integer comboId;

    @Column (nullable = false)
    @Schema (description = "Monto del pago", example = "19990")
    private Double monto;

    @Schema (description = "Estado del pago", example = "APROBADO")
    private String estado;

    @Schema (description = "Metodo usado para el pago", example = "Credito/Debito")
    private String metodoPago;

    @Schema (description = "Fecha de la transaccion", example = "2026-06-20")
    private LocalDate fecha;
}
