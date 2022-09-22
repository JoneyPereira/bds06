package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ReviewDTO {

    private Long id;

    @NotBlank(message = "Campo obrigatório!")
    private String text;

    private Long movieId;
    private UserMinDTO user;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, String text, Long movieId, UserMinDTO user) {
        this.id = id;
        this.text = text;
        this.movieId = movieId;
        this.user = user;
    }

    public ReviewDTO(Review entity) {
        id = entity.getId();
        text = entity.getText();
        movieId = entity.getMovie().getId();
        user = new UserMinDTO(entity.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public UserMinDTO getUser() {
        return user;
    }

    public void setUser(UserMinDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewDTO)) return false;
        ReviewDTO reviewDTO = (ReviewDTO) o;
        return Objects.equals(getId(), reviewDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
