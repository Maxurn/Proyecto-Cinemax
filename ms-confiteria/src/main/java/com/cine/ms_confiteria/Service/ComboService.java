package com.cine.ms_confiteria.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ms_confiteria.Client.PagoClient;
import com.cine.ms_confiteria.Model.Combo;
import com.cine.ms_confiteria.Model.DTO.ComboPagoDTO;
import com.cine.ms_confiteria.Model.DTO.PagoDTO;
import com.cine.ms_confiteria.Repository.ComboRepository;

@Service
public class ComboService {

    private static final Logger log = LoggerFactory.getLogger(ComboService.class);

    @Autowired
    private ComboRepository repository;

    @Autowired
    private PagoClient client;

    public List<Combo> ObtenerConfiteria(){
        log.info("Consultando los productos...");
        return repository.findAll();
    }

    public List<Combo> listarPorCategoria(String categoria) {
        log.info("Filtrando productos por esa categoria");
        return repository.findByCategoria(categoria);
    }
    public Combo buscarPorId(Integer id) {
        log.info("Se muestra combo por su id...");
        return repository.findById(id).orElse(null);
    }

    //==== METODOS DE FEIGN

    public PagoDTO comprarCombo(Integer comboId, String metPago){
        log.info("Comprando combo...");
        Combo combo = repository.findById(comboId).orElse(null);

        if (combo.getStock() <= 0) {
            log.warn("Intento de compra fallido: Sin stock");
            throw new RuntimeException("No hay Stock de este producto :C");
        }else{
            log.info("Stock actualizado exitosamente");
            combo.setStock(combo.getStock()-1);
            repository.save(combo);
        }

        ComboPagoDTO solicitud = new ComboPagoDTO();
        solicitud.setComboId(combo.getId());
        solicitud.setMetodoPago(metPago);
        solicitud.setMonto(combo.getValor());
        log.info("Registrando pago...");
        return client.pagarCombo(solicitud);
    }

    public List<PagoDTO> comprobarPago(Integer comboId){
        log.info("Consultando comprobantes de pago");
        return client.buscarPagoCombo(comboId);
    }
    //===
    
    public Combo guardarCombo(Combo combo) {
        log.info("Guardando nuevo combo...");
        return repository.save(combo);
    }

    public Combo actualizarCombo(Integer id, Combo detalles) {
        log.info("Actualizando detalles del combo...");
        Combo comboExistente = repository.findById(id).orElse(null);

        comboExistente.setNombre(detalles.getNombre());
        comboExistente.setValor(detalles.getValor());
        comboExistente.setStock(detalles.getStock());
        comboExistente.setCategoria(detalles.getCategoria());

        return repository.save(comboExistente);
    }

    public Void eliminarCombo(Integer id) {
        log.info("Eliminando producto...");
        repository.deleteById(id);
        return null;
    }
}
