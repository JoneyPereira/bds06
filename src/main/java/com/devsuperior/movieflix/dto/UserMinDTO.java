package com.devsuperior.movieflix.dto;


import com.devsuperior.movieflix.entities.User;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserMinDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Campo requerido")
	private String name;

	private String email;

	public UserMinDTO(User entity){
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
	}

	public UserMinDTO() {
	}

	public UserMinDTO(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
