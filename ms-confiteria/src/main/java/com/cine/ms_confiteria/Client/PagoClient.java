package com.cine.ms_confiteria.Client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cine.ms_confiteria.Model.DTO.ComboPagoDTO;
import com.cine.ms_confiteria.Model.DTO.PagoDTO;

@FeignClient(name = "ms-pago")
public interface PagoClient {

    @PostMapping("/pagar/combo")
    PagoDTO pagarCombo(@RequestBody ComboPagoDTO combo);

    @GetMapping("/combo/{comboId}")
    List<PagoDTO> buscarPagoCombo(@PathVariable("comboId") Integer id);
}
