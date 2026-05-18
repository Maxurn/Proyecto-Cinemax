package com.cine.ms_reserva.Client;

import com.cine.ms_reserva.Model.DTO.ButacaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "ms-butacas", url = "http://localhost:8085/api/v1/seats")
public interface ButacaClient {

    @PutMapping("/{seatId}/book")
    ButacaDTO reservarButaca(@PathVariable("seatId") Integer seatId);
}
