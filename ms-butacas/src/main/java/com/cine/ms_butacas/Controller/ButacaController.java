package com.cine.ms_butacas.Controller;

import com.cine.ms_butacas.Model.Butaca;
import com.cine.ms_butacas.Service.ButacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seats")
public class ButacaController {

    @Autowired
    private ButacaService butacaService;

    @GetMapping("/show/{funcionId}")
    public ResponseEntity<List<Butaca>> getButacas(@PathVariable Integer funcionId, @RequestParam Integer salaId) {

        List<Butaca> butacas = butacaService.getButacasPorFuncion(funcionId, salaId);
        return ResponseEntity.ok(butacas);
    }

    @PutMapping("/{seatId}/lock")
    public ResponseEntity<Butaca> lockSeat(@PathVariable Integer seatId) {
        Butaca butaca = butacaService.lockButaca(seatId);
        return ResponseEntity.ok(butaca);
    }


    @PutMapping("/{seatId}/book")
    public ResponseEntity<Butaca> bookSeat(@PathVariable Integer seatId) {
        Butaca butaca = butacaService.bookButaca(seatId);
        return ResponseEntity.ok(butaca);
    }
}
