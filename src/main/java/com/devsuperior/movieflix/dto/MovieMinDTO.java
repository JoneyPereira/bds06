package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Movie;

import java.util.Objects;

public class MovieMinDTO {

    private Long id;
    private String title;
    private String subTitle;
    private Integer year;
    private String imgUrl;
    private GenreDTO genre;

    public MovieMinDTO() {
    }

    public MovieMinDTO(Long id, String title, String subTitle, Integer year, String imgUrl, GenreDTO genre) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.year = year;
        this.imgUrl = imgUrl;
        this.genre = genre;
    }

    public MovieMinDTO(Movie entity) {
        id = entity.getId();
        title = entity.getTitle();
        subTitle = entity.getSubTitle();
        year = entity.getYear();
        imgUrl = entity.getImgUrl();
        genre = new GenreDTO (entity.getGenre());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieMinDTO)) return false;
        MovieMinDTO movieDTO = (MovieMinDTO) o;
        return Objects.equals(getId(), movieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
