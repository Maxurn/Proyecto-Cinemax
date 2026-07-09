package com.cine.ms_notificacion.Services;
import com.cine.ms_notificacion.Client.CarteleraClient;
import com.cine.ms_notificacion.Model.Dto.FuncionDetalleDTO;
import com.cine.ms_notificacion.Model.Dto.NotificacionRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacionService {
    private static final Logger log = LoggerFactory.getLogger(NotificacionService.class);
    private final CarteleraClient carteleraClient;

    public String enviarNotificacion(NotificacionRequest request) {
        log.info("Enviando notificación para funcion {} a {}", request.getFuncionId(), request.getCorreoDestino());
        FuncionDetalleDTO detalle = carteleraClient.obtenerDetalle(request.getFuncionId());
        
        log.info("Notificación generada con código {}", request.getCodigoTicket());
        return "su entrada para " + detalle.getNombrePelicula() + " esta lista! " +
               "Código: " + request.getCodigoTicket();
    }
}
