package com.devsuperior.movieflix.controllers.exceptions;

import com.devsuperior.movieflix.services.exceptions.DatabaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){

        ValidationError erro = new ValidationError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        erro.setError("Recurso não validado!");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getRequestURI());

        for(FieldError f : e.getBindingResult().getFieldErrors()){
            erro.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StanderError> database(DatabaseException e, HttpServletRequest request){

        StanderError erro = new StanderError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(HttpStatus.BAD_REQUEST.value());;
        erro.setError("Recurso não permitido!");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StanderError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){

        StanderError erro = new StanderError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setError("Recurso não encontrado!");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
