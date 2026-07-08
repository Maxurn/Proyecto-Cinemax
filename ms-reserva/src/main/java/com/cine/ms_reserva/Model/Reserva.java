package com.cine.ms_reserva.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reserva")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado", example = "1")
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    @Schema(description = "ID del usuario que realiza la reserva", example = "3")
    private Integer usuarioId;

    @Column(name = "funcion_id", nullable = false)
    @Schema(description = "ID de la funcion reservada", example = "5")
    private Integer funcionId;

    @Column(name = "butaca_id", nullable = false)
    @Schema(description = "ID de la butaca reservada", example = "14")
    private Integer butacaId;

    @Column(nullable = false)
    @Schema(description = "Monto pagado", example = "4500.0")
    private Double monto;

    @Column(name = "metodo_pago", nullable = false)
    @Schema(description = "Metodo de pago: TARJETA o EFECTIVO", example = "TARJETA")
    private String metodoPago;

    @Column(nullable = false)
    @Schema(description = "Estado de la reserva", example = "CONFIRMADA")
    private String estado;

    @Column(name = "fecha_reserva", nullable = false)
    @Schema(description = "Fecha en que se realizo la reserva", example = "2026-06-17")
    private LocalDate fechaReserva;
}
