package com.cine.ms_notificacion.Controller;
import com.cine.ms_notificacion.Model.Dto.NotificacionRequest;
import com.cine.ms_notificacion.Services.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificacionController {
private final NotificacionService notificationService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviar(@RequestBody NotificacionRequest request) {
        return ResponseEntity.ok(notificationService.enviarNotificacion(request));
    }
}
