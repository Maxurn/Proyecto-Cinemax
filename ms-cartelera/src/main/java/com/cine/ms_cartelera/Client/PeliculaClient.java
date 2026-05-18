package com.cine.ms_cartelera.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cine.ms_cartelera.Model.DTO.PeliculaDTO;

@FeignClient(name = "ms-peliculas", url = "http://localhost:8081/api/v1/peliculas")
public interface PeliculaClient {

    @GetMapping("/{id}")
    public PeliculaDTO buscarPorIdC(@PathVariable("id") Integer id);
}
