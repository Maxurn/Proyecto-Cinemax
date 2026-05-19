package com.cine.ms_notificacion.Services;
import com.cine.ms_notificacion.Client.CarteleraClient;
import com.cine.ms_notificacion.Model.Dto.FuncionDetalleDTO;
import com.cine.ms_notificacion.Model.Dto.NotificacionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacionService {
private final CarteleraClient carteleraClient;

    public String enviarNotificacion(NotificacionRequest request) {
        FuncionDetalleDTO detalle = carteleraClient.obtenerDetalle(request.getFuncionId());

        return "su entrada para " + detalle.getNombrePelicula() + " esta lista! " +
               "Código: " + request.getCodigoTicket();
    }
}
