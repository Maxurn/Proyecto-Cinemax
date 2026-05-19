package com.cine.ms_sucursal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ms_sucursal.Model.Cine;
import com.cine.ms_sucursal.Repository.CineRepository;

import java.util.List;
@Service
public class CineService {

    @Autowired
    private CineRepository repository;

    //LISTAR TODOS 
    public List<Cine> listarCines() {
        return repository.findAll();
    }

    //BUSCAR CINE POR EL ID
    public Cine buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    //GUARDAR/CREAR UN CINE
    public Cine guardarCine(Cine cine) {
        return repository.save(cine);
    }

    //BORRAR POR ID
    public void borrarCine(Integer id) {
        repository.deleteById(id);
    }

    //ACTUALIZAR UN CINE
    public Cine actualizarCine(Integer id, Cine cineDetalles) {

        Cine cineExistente = repository.findById(id).orElse(null);

        cineExistente.setNombre(cineDetalles.getNombre());
        cineExistente.setDireccion(cineDetalles.getDireccion());
        cineExistente.setCiudad(cineDetalles.getCiudad());

        return repository.save(cineExistente);
    }
    //LISTAR POR CIUDAD
    public List<Cine> listarPorCiudad(String ciudad) {
        return repository.findByCiudad(ciudad);
    }
}