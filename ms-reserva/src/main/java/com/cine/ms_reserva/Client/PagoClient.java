package com.cine.ms_reserva.Client;

import com.cine.ms_reserva.Model.DTO.PagoRespuestaDTO;
import com.cine.ms_reserva.Model.DTO.PagoReservaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-pago")
public interface PagoClient {

    @PostMapping("/pagar/reserva")
    PagoRespuestaDTO pagar(@RequestBody PagoReservaDTO pagoReservaDTO);
}
