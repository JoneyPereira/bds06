package com.devsuperior.movieflix.repositores;

import com.devsuperior.movieflix.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    @Query("SELECT DISTINCT obj FROM Review obj "
//            + "INNER JOIN obj.movie mo "
//            + "WHERE (COALESCE(:movie, 0) IS NULL OR mo IN :movie) ")
//    Page<Review> find(Movie movie, Pageable pageable);
//
//    @Query("SELECT obj FROM Movie obj JOIN FETCH obj.reviews WHERE obj IN :movie")
//    List<Review> findMoviesWithReview(List<Review> reviews);

}
