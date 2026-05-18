package com.cine.ms_confiteria.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ms_confiteria.Client.PagoClient;
import com.cine.ms_confiteria.Model.Combo;
import com.cine.ms_confiteria.Model.DTO.ComboPagoDTO;
import com.cine.ms_confiteria.Model.DTO.PagoDTO;
import com.cine.ms_confiteria.Repository.ComboRepository;

@Service
public class ComboService {

    @Autowired
    private ComboRepository repository;

    @Autowired
    private PagoClient client;

    public List<Combo> ObtenerConfiteria(){
        return repository.findAll();
    }

    public List<Combo> listarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }
    public Combo buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    //==== METODOS DE FEIGN

    public PagoDTO comprarCombo(Integer comboId, String metPago){
        Combo combo = repository.findById(comboId).orElse(null);

        if (combo.getStock() <= 0) {
            throw new RuntimeException("No hay Stock de este producto :C");
        }else{
            combo.setStock(combo.getStock()-1);
            repository.save(combo);
        }

        ComboPagoDTO solicitud = new ComboPagoDTO();
        solicitud.setComboId(combo.getId());
        solicitud.setMetodoPago(metPago);
        solicitud.setMonto(combo.getValor());

        return client.pagarCombo(solicitud);
    }

    public List<PagoDTO> comprobarPago(Integer comboId){
        return client.buscarPagoCombo(comboId);
    }
    //===
    
    public Combo guardarCombo(Combo combo) {
        return repository.save(combo);
    }

    public Combo actualizarCombo(Integer id, Combo detalles) {
        Combo comboExistente = repository.findById(id).orElse(null);

        comboExistente.setNombre(detalles.getNombre());
        comboExistente.setValor(detalles.getValor());
        comboExistente.setStock(detalles.getStock());
        comboExistente.setCategoria(detalles.getCategoria());

        return repository.save(comboExistente);
    }

    public Void eliminarCombo(Integer id) {
        repository.deleteById(id);
        return null;
    }
}
