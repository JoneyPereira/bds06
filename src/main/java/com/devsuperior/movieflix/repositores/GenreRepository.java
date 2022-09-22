package com.devsuperior.movieflix.repositores;

import com.devsuperior.movieflix.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {



}
