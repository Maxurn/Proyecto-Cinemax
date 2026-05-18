package com.cine.ms_confiteria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cine.ms_confiteria.Model.Combo;
import com.cine.ms_confiteria.Model.DTO.PagoDTO;
import com.cine.ms_confiteria.Service.ComboService;

@RestController
@RequestMapping("/api/v1/confiteria")
public class ComboController {

    @Autowired
    private ComboService serviceC;

    //LISTAR CONFITES
    @GetMapping
    public List<Combo> listarTodo(){
        return serviceC.ObtenerConfiteria();
    }
    //FILTRAR POR CATEGORIA DE COMBO
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Combo>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(serviceC.listarPorCategoria(categoria));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Combo> buscarPorIdController(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceC.buscarPorId(id));
    }
    //CREAR COMBO EN BBDD
    @PostMapping
    public ResponseEntity<Combo> guardarCombo(@RequestBody Combo combo) {
        return new ResponseEntity<Combo>(serviceC.guardarCombo(combo), HttpStatus.CREATED);
    }
    //ACTUALIZAR COMBO
    @PutMapping("/{id}")
    public ResponseEntity<Combo> actualizarCombo(@PathVariable Integer id, @RequestBody Combo detalles) {
        Combo combActualizado = serviceC.actualizarCombo(id, detalles);
        return ResponseEntity.ok(combActualizado);
    }
    //BORRAR COMBO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCombo(@PathVariable Integer id) {
        Combo combo = serviceC.buscarPorId(id);
        
        if (combo == null) {
            return ResponseEntity.notFound().build();
        }else{
        serviceC.eliminarCombo(id);
        return ResponseEntity.noContent().build();
        }
    }

    //COMPRAR Y ANOTAR EN MS-PAGO
    @PostMapping("/comprar/{comboId}")
    public PagoDTO comprarCombo( @PathVariable Integer comboId, String metodoPag){
        PagoDTO comprobante = serviceC.comprarCombo(comboId, metodoPag);
        return comprobante;
    }
    //COMPROBAR PAGO
    @GetMapping("/comprobar-pago/{comboId}")
    public ResponseEntity<List<PagoDTO>> comprobarPago(@PathVariable Integer comboId) {
        return ResponseEntity.ok(serviceC.comprobarPago(comboId));
    }


}
