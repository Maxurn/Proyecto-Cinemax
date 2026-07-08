package com.cine.ms_confiteria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cine.ms_confiteria.Model.Combo;
import com.cine.ms_confiteria.Model.DTO.PagoDTO;
import com.cine.ms_confiteria.Service.ComboService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/confiteria")
@Tag(name = "Confiteria", description = "Operaciones relacionadas con la Confiteria.")
public class ComboController {

    @Autowired
    private ComboService serviceC;

    //LISTAR CONFITES
    @Operation(summary = "Listar todos los combos", description = "Retorna la lista completa de los combos.")
    @ApiResponse(responseCode = "200", description = "Operacion exitosa")
    @GetMapping
    public List<Combo> listarTodo(){
        return serviceC.ObtenerConfiteria();
    }
    //FILTRAR POR CATEGORIA DE COMBO
    @Operation(summary = "Listar segun categoria", description = "Retorna lista completa de los combos con esa categoria elegida.")
    @ApiResponse(responseCode = "200", description = "Operacion Exitosa.")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Combo>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(serviceC.listarPorCategoria(categoria));
    }
    //Buscar por Id
    @Operation(summary = "Busca por ID", description = "Retorna combo segun la id entregada.")
    @ApiResponse(responseCode = "200", description = "Operacion Exitosa.")
    @GetMapping("/{id}")
    public ResponseEntity<Combo> buscarPorIdController(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceC.buscarPorId(id));
    }
    //CREAR COMBO EN BBDD
    @Operation(summary = "Crear nuevo combo", description = "Registra un nuevo combo en la BBDD")
    @ApiResponse(responseCode = "201", description = "Combo creado.")
    @PostMapping
    public ResponseEntity<Combo> guardarCombo(@RequestBody Combo combo) {
        return new ResponseEntity<Combo>(serviceC.guardarCombo(combo), HttpStatus.CREATED);
    }
    //ACTUALIZAR COMBO
    @Operation(summary = "Actualizar combo", description = "Actualiza los datos de un combo.")
    @PutMapping("/{id}")
    public ResponseEntity<Combo> actualizarCombo(@PathVariable Integer id, @RequestBody Combo detalles) {
        Combo combActualizado = serviceC.actualizarCombo(id, detalles);
        return ResponseEntity.ok(combActualizado);
    }
    //BORRAR COMBO
    @Operation(summary = "Eliminar combo", description = "Borra un combo segun su ID.")
    @ApiResponse(responseCode = "204", description = "Combo eliminado.")
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
    @Operation(summary = "Anotar pago en MS-PAGO", description = "Escribe o registra un nuevo pago en el microservicio de registro ms-pago.")
    @PostMapping("/comprar/{comboId}")
    public PagoDTO comprarCombo( @PathVariable Integer comboId, String metodoPag){
        PagoDTO comprobante = serviceC.comprarCombo(comboId, metodoPag);
        return comprobante;
    }
    //COMPROBAR PAGO
    @Operation(summary = "Comprobar estado de pago", description = "Verifica el pago asociado al combo.")
    @GetMapping("/comprobar-pago/{comboId}")
    public ResponseEntity<List<PagoDTO>> comprobarPago(@PathVariable Integer comboId) {
        return ResponseEntity.ok(serviceC.comprobarPago(comboId));
    }


}
