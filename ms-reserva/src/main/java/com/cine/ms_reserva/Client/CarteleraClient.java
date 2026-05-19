package com.cine.ms_reserva.Client;

import com.cine.ms_reserva.Model.DTO.FuncionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cartelera", url = "http://localhost:8083/api/v1/funciones")
public interface CarteleraClient {

    @GetMapping("/{id}")
    FuncionDTO obtenerFuncion(@PathVariable("id") Integer id);
}
