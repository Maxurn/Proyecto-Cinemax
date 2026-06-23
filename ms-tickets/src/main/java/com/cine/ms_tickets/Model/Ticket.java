package com.cine.ms_tickets.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")

@Data
@Schema(description = "Entidad que representa un ticket de reserva")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Schema(description = "ID autogenerado", example = "1")
    private Integer id;
    
    @Column( nullable = false)
    @Schema(description = "ID de la reserva asociada", example = "42")
    private Integer reservaId;
    
    @Column( unique = true, nullable = false)
     @Schema(description = "Código QR único del ticket", example = "TKT-1718652345678")
    private String codigoQr;
    
    @Column(nullable = false)
    @Schema(description = "Estado del ticket: ACTIVO o USADO", example = "ACTIVO")
    private String estado;
}