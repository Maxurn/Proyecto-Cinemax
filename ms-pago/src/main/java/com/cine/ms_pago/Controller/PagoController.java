package com.cine.ms_pago.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cine.ms_pago.Model.Pago;
import com.cine.ms_pago.Model.DTO.PagoConfiteriaDTO;
import com.cine.ms_pago.Model.DTO.PagoReservaDTO;
import com.cine.ms_pago.Service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Operaciones para procesar y consultar pagos de reserva y confiteria")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    //MS - RESERVA

    @PostMapping("/pagar/reserva") //api/v1/pagos/pagar/reserva, ingresar reservaId, monto y metodoPago
    @Operation(summary = "Procesar pago de reserva", description = "Registra el pago asociado a ms-reserva")
    @ApiResponse(responseCode = "200", description = "Pago procesado exitosamente")
    public Pago pagar(@RequestBody PagoReservaDTO reserva) {
        return pagoService.procesarPago(reserva);
    }

    @GetMapping("reserva/{reservaId}") //se busca a partir de la reservaId para comprobar el Aprobado o Rechazado
    @Operation(summary = "Buscar pagos por ID de reserva", description = "Retorna el historial de pagos asociados a un reservaId")
    @ApiResponse(responseCode = "200", description = "Lista de pagos encontrada")
    public List<Pago> buscarPagoIdRes(@PathVariable Integer reservaId) {
        List<Pago> pago = pagoService.listarReservaId(reservaId);
        return pago;
    }

    // MS-CONFITERIA

    @PostMapping("/pagar/combo") //api/v1/pagos/pagar/combo, ingresar comboId, monto y metodoPago
    @Operation(summary = "Procesar pago de combo", description = "Registra el pago de un combo proveniente de ms-confiteria")
    @ApiResponse(responseCode = "200", description = "Pago de combo procesado exitosamente")
    public Pago pagarCombo(@RequestBody PagoConfiteriaDTO combo) {
        return pagoService.procesarPagoConfiteria(combo);
    }

    @GetMapping("combo/{comboId}") //se busca a partir del comboId para comprobar el Aprobado o Rechazado
    @Operation(summary = "Buscar pagos por ID de combo", description = "Retorna el historial de pagos asociados a un comboId")
    @ApiResponse(responseCode = "200", description = "Pago de combo encontrado")
    public List<Pago> BuscarPagoCombo(@PathVariable Integer comboId) {
        List<Pago> pago = pagoService.listarComboId(comboId);
        return pago;
    }
}