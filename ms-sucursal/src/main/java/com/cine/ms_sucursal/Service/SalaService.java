package com.cine.ms_sucursal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ms_sucursal.Model.Cine;
import com.cine.ms_sucursal.Model.Sala;
import com.cine.ms_sucursal.Repository.CineRepository;
import com.cine.ms_sucursal.Repository.SalaRepository;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private CineRepository cineRepository;

    // LISTAR SALAS
    public List<Sala> listarTodas() {
        return salaRepository.findAll();
    }
    //SALAS POR CINE
    public List<Sala> listarSalasPorCine(Integer cineId) {
        return salaRepository.findByCineId(cineId);
    }
    //CREAR SALA Y AGREGARLA DIRECTAMENTE A UN CINE (si existe)
    public Sala agregarSalaACine(Integer cineId, Sala sala) {
        Cine cine = cineRepository.findById(cineId).orElse(null);
        
        if (cine != null) {
            sala.setCine(cine); 
            return salaRepository.save(sala);
        }
        return null;
    }
    //BUSCAR SALA POR ID
    public Sala buscarPorId(Integer id) {
        return salaRepository.findById(id).orElse(null);
    }
    //BORRAR SALA (ID)
    public void eliminar(Integer id) {
        salaRepository.deleteById(id);
    }
    //ACTUALIZAR SALA
    public Sala actualizarSala(Integer id, Sala salaDetalles) {
    Sala salaExistente = salaRepository.findById(id).orElse(null);

        salaExistente.setNombre(salaDetalles.getNombre());
        salaExistente.setCapacidad(salaDetalles.getCapacidad());
        if (salaDetalles.getCine() != null){
            salaExistente.setCine(salaDetalles.getCine());
        }
        return salaRepository.save(salaExistente);
    }
}