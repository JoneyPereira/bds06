package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
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
public class GenreService {
    private static Logger Logger = LoggerFactory.getLogger(GenreService.class);

    @Autowired
    private GenreRepository repository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private AuthService authService;


    public List<GenreDTO> findAll() {
        List<Genre> list = repository.findAll();
        return list.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<GenreDTO> findAllPaged(Pageable pageable){
        Page<Genre> list = repository.findAll(pageable);
        return list.map(x -> new GenreDTO(x));
    }

    @Transactional(readOnly = true)
    public GenreDTO findById(Long id){
        Optional<Genre> obj = repository.findById(id);
        Genre entity = obj.orElseThrow(() -> new ResourceNotFoundException("Genre não encontrado, id: " + id));
        return new GenreDTO(entity);
    }

}
