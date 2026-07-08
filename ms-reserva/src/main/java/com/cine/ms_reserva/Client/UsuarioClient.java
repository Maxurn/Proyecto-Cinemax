package com.cine.ms_reserva.Client;

import com.cine.ms_reserva.Model.DTO.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-usuario")
public interface UsuarioClient {

    @GetMapping("/{id}")
    UsuarioDTO obtenerUsuario(@PathVariable("id") Integer id);
}
