package com.cine.ms_cartelera.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cine.ms_cartelera.Model.DTO.SalaDTO;

@FeignClient(name = "ms-sucursal")
public interface CineClient {

    @GetMapping("/{id}")
    public SalaDTO buscarPorIdC(@PathVariable("id") Integer id);

}
