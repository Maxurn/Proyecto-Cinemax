package com.cine.ms_peliculas.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cine.ms_peliculas.Model.Pelicula;
import java.util.List;


@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>{

    List<Pelicula> findByRatingEdad(String ratingEdad);
}
