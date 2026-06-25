package com.cine.ms_peliculas.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ms_peliculas.Model.Pelicula;
import com.cine.ms_peliculas.Repository.PeliculaRepository;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(PeliculaService.class);

    public List<Pelicula> listarTodo(){
        logger.info("Mostrando todas las películas");
        return repository.findAll();
    }
    public Pelicula buscarId(Integer id){
        logger.info("Buscando película con ID: {}", id);
        return repository.findById(id).orElseThrow(null);
    }
    public List<Pelicula> listarPorRating(String rating) {
        logger.info("Listando películas por rating: {}", rating);
        return repository.findByRatingEdad(rating);
    }
    public Pelicula guardar(Pelicula peli){
        logger.info("Guardando nueva película... ");
        return repository.save(peli);
    }
    public void borrar(Integer id){
        Pelicula peli = repository.findById(id).orElse(null);
        logger.info("Intentando borrar película");
        if (peli == null) {
            logger.warn("Intento de borrado fallido", id);
            throw new RuntimeException(id + " no esta en la base de datos.");
        }else{
            repository.deleteById(id);
            logger.info("Película borrada correctamente", id);
        }
    }
    public Pelicula actualizarPelicula(Integer id, Pelicula peli) {
        Pelicula peliExis = repository.findById(id).orElse(null);

        peliExis.setTituloCinta(peli.getTituloCinta());
        peliExis.setRatingEdad(peli.getRatingEdad());
        peliExis.setDuracion(peli.getDuracion());

        logger.info("Película actualizada satisfactoriamente", id);
        return repository.save(peliExis);   
    }
}
