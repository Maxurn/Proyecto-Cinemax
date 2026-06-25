package com.cine.ms_sucursal.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ms_sucursal.Model.Cine;
import com.cine.ms_sucursal.Model.Sala;
import com.cine.ms_sucursal.Repository.CineRepository;
import com.cine.ms_sucursal.Repository.SalaRepository;

@Service
public class SalaService {

    private static final Logger log = LoggerFactory.getLogger(CineService.class);

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private CineRepository cineRepository;

    // LISTAR SALAS
    public List<Sala> listarTodas() {
        log.info("Mostrando salas...");
        return salaRepository.findAll();
    }
    //SALAS POR CINE
    public List<Sala> listarSalasPorCine(Integer cineId) {
        log.info("Mostrando salas por el cine...");
        return salaRepository.findByCineId(cineId);
    }
    //CREAR SALA Y AGREGARLA DIRECTAMENTE A UN CINE (si existe)
    public Sala agregarSalaACine(Integer cineId, Sala sala) {
        Cine cine = cineRepository.findById(cineId).orElse(null);
        log.info("Sala creada y agregada al " + cineId);
        if (cine != null) {
            sala.setCine(cine); 
            return salaRepository.save(sala);
        }
        return null;
    }
    //BUSCAR SALA POR ID
    public Sala buscarPorId(Integer id) {
        log.info("Mostrando sala por salaId...");
        return salaRepository.findById(id).orElse(null);
    }
    //BORRAR SALA (ID)
    public void eliminar(Integer id) {
        log.info("Borrando sala...");
        salaRepository.deleteById(id);
    }
    //ACTUALIZAR SALA
    public Sala actualizarSala(Integer id, Sala salaDetalles) {
        log.info("Sala Actualizada!");
    Sala salaExistente = salaRepository.findById(id).orElse(null);

        salaExistente.setNombre(salaDetalles.getNombre());
        salaExistente.setCapacidad(salaDetalles.getCapacidad());
        if (salaDetalles.getCine() != null){
            salaExistente.setCine(salaDetalles.getCine());
        }
        return salaRepository.save(salaExistente);
    }
}