package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositores.MovieRepository;
import com.devsuperior.movieflix.repositores.ReviewRepository;
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
public class ReviewService {
    private static Logger Logger = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private ReviewRepository repository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private AuthService authService;


    public List<ReviewDTO> findAll() {
        List<Review> list = repository.findAll();
        return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ReviewDTO> findAllPaged(Pageable pageable){
        Page<Review> list = repository.findAll(pageable);
        return list.map(x -> new ReviewDTO(x));
    }

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id){
        Optional<Review> obj = repository.findById(id);
        Review entity = obj.orElseThrow(() -> new ResourceNotFoundException("Review não encontrado, id: " + id));
        return new ReviewDTO(entity);
    }

//    @Transactional(readOnly = true)
//    public List<ReviewDTO> findByMovieReviews(Long movieId, Pageable pageable){
//        Movie movie = movieRepository.getOne(movieId);
//        Page<Review> list = repository.find(movie, pageable);
//
//        return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
//    }

    @Transactional
    public ReviewDTO insert(ReviewDTO dto){

        User user = authService.authenticated();

        Review entity = new Review();
        entity.setMovie(movieRepository.getOne(dto.getMovieId()));
        entity.setUser(user);
        entity.setText(dto.getText());
        repository.save(entity);

        return new ReviewDTO(entity);
    }
}
