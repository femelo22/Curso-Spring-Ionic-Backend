package com.br.curso.resources.exception;

import java.time.Instant;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.curso.services.exception.AuthorizationException;
import com.br.curso.services.exception.DataIntegrityException;
import com.br.curso.services.exception.ObjectNotFoundException;


@ControllerAdvice
public class ResourceExceptionHandle {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> elementNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Não encontrado");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);		
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> integrityViolation(DataIntegrityException e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Integridade de dados");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError error = new ValidationError();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		error.setError("Erro de validação");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		for(FieldError x:  e.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);		
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.FORBIDDEN.value());
		error.setError("Acesso negado");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);		
	}
}
