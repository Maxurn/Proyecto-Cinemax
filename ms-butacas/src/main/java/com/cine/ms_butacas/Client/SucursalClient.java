package com.cine.ms_butacas.Client;

import com.cine.ms_butacas.Model.DTO.SalaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-sucursal")
public interface SucursalClient {

    @GetMapping("/{id}")
    SalaDTO buscarPorIdC(@PathVariable("id") Integer id);
}
