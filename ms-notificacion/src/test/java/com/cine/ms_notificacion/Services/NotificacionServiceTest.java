package com.cine.ms_notificacion.Services;

import com.cine.ms_notificacion.Client.CarteleraClient;
import com.cine.ms_notificacion.Model.Dto.FuncionDetalleDTO;
import com.cine.ms_notificacion.Model.Dto.NotificacionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceTest {

    @Mock
    private CarteleraClient carteleraClient;

    @InjectMocks
    private NotificacionService notificacionService;

    private NotificacionRequest request;
    private FuncionDetalleDTO detalle;

    @BeforeEach
    void setUp() {
        request = new NotificacionRequest();
        request.setCorreoDestino("juan@cine.cl");
        request.setFuncionId(5);
        request.setCodigoTicket("TKT-123456");

        detalle = new FuncionDetalleDTO();
        detalle.setId(5);
        detalle.setNombrePelicula("Avengers");
        detalle.setNombreCine("Cine Hoyts");
        detalle.setNombreSala("Sala 3");
    }

    @Test
    void enviarNotificacion_construyeMensajeConPeliculaYCodigo() {
        when(carteleraClient.obtenerDetalle(5)).thenReturn(detalle);

        String resultado = notificacionService.enviarNotificacion(request);

        assertTrue(resultado.contains("Avengers"));
        assertTrue(resultado.contains("TKT-123456"));
        verify(carteleraClient, times(1)).obtenerDetalle(5);
    }

    @Test
    void enviarNotificacion_llamaAlClienteConFuncionIdCorrecto() {
        when(carteleraClient.obtenerDetalle(request.getFuncionId())).thenReturn(detalle);

        notificacionService.enviarNotificacion(request);

        verify(carteleraClient).obtenerDetalle(5);
    }
}