package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.AuthService;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

	@Autowired
	private ReviewService service;

	@Autowired
	AuthService authService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ReviewDTO> findById(@PathVariable Long id){

		ReviewDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PreAuthorize("hasAnyRole('MEMBER')")
	@PostMapping
	public ResponseEntity<ReviewDTO> insert(@Valid @RequestBody ReviewDTO dto){

		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}
