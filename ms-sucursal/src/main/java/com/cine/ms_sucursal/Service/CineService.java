package com.cine.ms_sucursal.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ms_sucursal.Model.Cine;
import com.cine.ms_sucursal.Repository.CineRepository;

import java.util.List;
@Service
public class CineService {

    private static final Logger log = LoggerFactory.getLogger(CineService.class);

    @Autowired
    private CineRepository repository;

    //LISTAR TODOS 
    public List<Cine> listarCines() {
        log.info("Mostrando cines...");
        return repository.findAll();
    }

    //BUSCAR CINE POR EL ID
    public Cine buscarPorId(Integer id) {
        log.info("Mostrando cine...");
        return repository.findById(id).orElse(null);
    }

    //GUARDAR/CREAR UN CINE
    public Cine guardarCine(Cine cine) {
        log.info("Cine creado!");
        return repository.save(cine);
    }

    //BORRAR POR ID
    public void borrarCine(Integer id) {
        log.info("Cine Borrado!");
        repository.deleteById(id);
    }

    //ACTUALIZAR UN CINE
    public Cine actualizarCine(Integer id, Cine cineDetalles) {

        Cine cineExistente = repository.findById(id).orElse(null);

        cineExistente.setNombre(cineDetalles.getNombre());
        cineExistente.setDireccion(cineDetalles.getDireccion());
        cineExistente.setCiudad(cineDetalles.getCiudad());
        log.info("Cine actualizado!");
        return repository.save(cineExistente);
    }
    //LISTAR POR CIUDAD
    public List<Cine> listarPorCiudad(String ciudad) {
        log.info("Listando los cines de esa ciudad...");
        return repository.findByCiudad(ciudad);
    }
}