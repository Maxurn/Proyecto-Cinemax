package com.cine.ms_usuario.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entitdad que representa un usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id autogenerado", example = "1")
    private Integer id;

    @Column(nullable = false)
    @Schema(description = "Nombre del usuario", example = "Juan")
    private String nombre;

    @Column(name = "segundo_nombre")
    @Schema(description = "Segundo nombre (opcional)", example = "Carlos")
    private String segundoNombre;

    @Column(nullable = false)
    @Schema(description = "Apellido del usuario", example = "Pérez")
    private String apellido;

    @Column(nullable = false, unique = true)
    @Schema(description = "Correo electrónico único", example = "juan@cine.cl")
    private String correo;

    @Column(nullable = false)
    @Schema(description = "Teléfono de contacto", example = "+56912345678")
    private String telefono;

    @Column(nullable = false, unique = true, length = 8)
    @Schema(description = "Rut sin puntos ni dígito verificador, exactamente 8 dígitos", example = "12345678")
    private String rut;
}
