package com.cine.ms_notificacion.Client;
import com.cine.ms_notificacion.Model.Dto.FuncionDetalleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "ms-cartelera")

public interface CarteleraClient {
@GetMapping("/{id}")
    FuncionDetalleDTO obtenerDetalle(@PathVariable("id") Integer id);
}
