package com.cine.ms_peliculas.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ms_peliculas.Model.Pelicula;
import com.cine.ms_peliculas.Repository.PeliculaRepository;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository repository;

    public List<Pelicula> listarTodo(){
        return repository.findAll();
    }
    public Pelicula buscarId(Integer id){
        return repository.findById(id).orElseThrow(null);
    }
    public List<Pelicula> listarPorRating(String rating) {
        return repository.findByRatingEdad(rating);
    }
    public Pelicula guardar(Pelicula peli){
        return repository.save(peli);
    }
    public void borrar(Integer id){
        Pelicula peli = repository.findById(id).orElse(null);
        if (peli == null) {
            throw new RuntimeException(id + " no esta en la base de datos.");
        }else{
            repository.deleteById(id);
        }
    }
    public Pelicula actualizarPelicula(Integer id, Pelicula peli) {
    Pelicula peliExis = repository.findById(id).orElse(null);

        peliExis.setTituloCinta(peli.getTituloCinta());
        peliExis.setRatingEdad(peli.getRatingEdad());
        peliExis.setDuracion(peli.getDuracion());
        return repository.save(peliExis);   
    }
}
