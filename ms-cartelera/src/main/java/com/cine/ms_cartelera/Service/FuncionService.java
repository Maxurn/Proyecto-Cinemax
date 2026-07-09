package com.cine.ms_cartelera.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ms_cartelera.Client.CineClient;
import com.cine.ms_cartelera.Client.PeliculaClient;
import com.cine.ms_cartelera.Model.Funcion;
import com.cine.ms_cartelera.Model.DTO.FuncionDetalleDTO;
import com.cine.ms_cartelera.Model.DTO.PeliculaDTO;
import com.cine.ms_cartelera.Model.DTO.SalaDTO;
import com.cine.ms_cartelera.Repository.FuncionRepository;

@Service
public class FuncionService {

    private static final Logger log = LoggerFactory.getLogger(FuncionService.class);

    @Autowired
    private FuncionRepository repository;

    @Autowired
    private PeliculaClient peliClient;

    @Autowired
    private CineClient cineClient;

    public FuncionDetalleDTO obtenerDetalle(Integer id){
        Funcion func = repository.findById(id).orElse(null);

        PeliculaDTO peli = peliClient.buscarPorIdC(func.getPeliculaId());
        SalaDTO sala = cineClient.buscarPorIdC(func.getSalaId());

        FuncionDetalleDTO detalleFuncion = new FuncionDetalleDTO();
        detalleFuncion.setId(func.getId());
        detalleFuncion.setFecha(func.getFecha());
        detalleFuncion.setHora(func.getHora());
        detalleFuncion.setPrecioEntrada(func.getPrecioEntrada());

        detalleFuncion.setNombrePelicula(peli.getTituloCinta());
        detalleFuncion.setNombreCine(sala.getCine().getNombre());
        detalleFuncion.setNombreSala(sala.getNombre());

        log.info("Datos consultados, entregando detalle");
        return detalleFuncion;
    }
    //para listar todo
    public List<FuncionDetalleDTO> ListarFunciones(){
        List<Funcion> funciones = repository.findAll();
        List<FuncionDetalleDTO> funcionesDetalle = new ArrayList<>();

        for (Funcion func: funciones){
            PeliculaDTO peli = peliClient.buscarPorIdC(func.getPeliculaId());
            SalaDTO sala = cineClient.buscarPorIdC(func.getSalaId());
            FuncionDetalleDTO detalleF = new FuncionDetalleDTO();
            detalleF.setId(func.getId());
            detalleF.setFecha(func.getFecha());
            detalleF.setHora(func.getHora());
            detalleF.setPrecioEntrada(func.getPrecioEntrada());

            detalleF.setNombrePelicula(peli.getTituloCinta());
            detalleF.setNombreSala(sala.getNombre());
            detalleF.setNombreCine(sala.getCine().getNombre());

            log.info("Mostrando todas las funciones disponibles...");
            funcionesDetalle.add(detalleF);
        }
        return funcionesDetalle;
        
    } //para listar por pelicula Id
    public List<FuncionDetalleDTO> listarPorPelicula(Integer peliculaId) {
        List<Funcion> funciones = repository.findByPeliculaId(peliculaId);
        List<FuncionDetalleDTO> funcionesDetalle = new ArrayList<>();

        for (Funcion func : funciones) {
            PeliculaDTO peli = peliClient.buscarPorIdC(func.getPeliculaId());
            SalaDTO sala = cineClient.buscarPorIdC(func.getSalaId());
            FuncionDetalleDTO detalleF = new FuncionDetalleDTO();
            detalleF.setId(func.getId());
            detalleF.setFecha(func.getFecha());
            detalleF.setHora(func.getHora());
            detalleF.setPrecioEntrada(func.getPrecioEntrada());
            detalleF.setNombrePelicula(peli.getTituloCinta());
            detalleF.setNombreSala(sala.getNombre());
            detalleF.setNombreCine(sala.getCine().getNombre());

            log.info("Mostrando funciones con esa pelicula...");
            funcionesDetalle.add(detalleF);
        }
        return funcionesDetalle;
    }

    public Funcion nuevaFuncion(Funcion funcion) {
        log.info("Funcion guardada!");
        return repository.save(funcion);
    }

    public Funcion actualizarFuncion(Integer id, Funcion detalles) {
        Funcion funcionExistente = repository.findById(id).orElse(null);

        if (funcionExistente == null) {
            log.warn("Esa funcion no existe!");
            return null; 
        }else{

    
            funcionExistente.setPeliculaId(detalles.getPeliculaId());
            funcionExistente.setSalaId(detalles.getSalaId());
            funcionExistente.setFecha(detalles.getFecha());
            funcionExistente.setHora(detalles.getHora());
            funcionExistente.setPrecioEntrada(detalles.getPrecioEntrada());

            log.info("Funcion Actualizada");
            return repository.save(funcionExistente);
        }
    }

    public Void eliminarFuncion(Integer id) {
        log.info("Funcion retirada!");
        repository.deleteById(id);
        return null;
    }
}
