package com.cine.ms_pago.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cine.ms_pago.Model.Pago;
import com.cine.ms_pago.Model.DTO.PagoConfiteriaDTO;
import com.cine.ms_pago.Model.DTO.PagoReservaDTO;
import com.cine.ms_pago.Service.PagoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    //MS - RESERVA

    @PostMapping("/pagar/reserva") //api/v1/pagos/pagar/reserva, ingresar reservaId, monto y metodoPago
    public Pago pagar(@RequestBody PagoReservaDTO reserva) {
        return pagoService.procesarPago(reserva);
    }

    @GetMapping("reserva/{reservaId}") //se busca a partir de la reservaId para comprobar el Aprobado o Rechazado
    public List<Pago> buscarPagoIdRes(@PathVariable Integer reservaId) {
        List<Pago> pago = pagoService.listarReservaId(reservaId);
        return pago;
    }

    // MS-CONFITERIA

    @PostMapping("/pagar/combo") //api/v1/pagos/pagar/combo, ingresar comboId, monto y metodoPago
    public Pago pagarCombo(@RequestBody PagoConfiteriaDTO combo) {
        return pagoService.procesarPagoConfiteria(combo);
    }

    @GetMapping("combo/{comboId}") //se busca a partir del comboId para comprobar el Aprobado o Rechazado
    public List<Pago> BuscarPagoCombo(@PathVariable Integer comboId) {
        List<Pago> pago = pagoService.listarComboId(comboId);
        return pago;
    }
}