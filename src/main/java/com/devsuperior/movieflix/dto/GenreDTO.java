package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Genre;

import java.util.Objects;

public class GenreDTO {

    private Long id;
    private String name;

    public GenreDTO() {
    }

    public GenreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreDTO(Genre entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenreDTO)) return false;
        GenreDTO genreDTO = (GenreDTO) o;
        return Objects.equals(getId(), genreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
