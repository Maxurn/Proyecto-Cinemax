package com.cine.ms_notificacion.Controller;
import com.cine.ms_notificacion.Model.Dto.NotificacionRequest;
import com.cine.ms_notificacion.Services.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notificaciones", description = "Gestion de notificaciones del cine")
public class NotificacionController {
private final NotificacionService notificationService;

    @PostMapping("/enviar")
    @Operation(summary = "Enviar notificacion", description = "Envia una notificacion al usuario con el detalle de su entrada")
    @ApiResponse(responseCode = "200", description = "Notificacion enviada exitosamente")
    public ResponseEntity<String> enviar(@RequestBody NotificacionRequest request) {
        return ResponseEntity.ok(notificationService.enviarNotificacion(request));
    }
}
