package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositores.GenreRepository;
import com.devsuperior.movieflix.repositores.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private static Logger Logger = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository repository;

    @Autowired
    private GenreRepository genreRepository;


    public List<MovieDTO> findAll() {
        List<Movie> list = repository.findAll();
        return list.stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<MovieDTO> findAllPaged(Long categoryId, Pageable pageable){
        Page<Movie> page = repository.findAll(pageable);
        return page.map(x -> new MovieDTO(x));
    }

    @Transactional(readOnly = true)
    public Page<MovieDTO> findByGenre(Long genreId, Pageable pageable){
        Genre genre = (genreId == 0L) ? null : genreRepository.getOne(genreId);
        Page<Movie> page = repository.find(genre, pageable);
        return page.map(x -> new MovieDTO(x));
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id){
        Optional<Movie> obj = repository.findById(id);
        Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado, id: " + id));
        return new MovieDTO(entity);
    }
}
