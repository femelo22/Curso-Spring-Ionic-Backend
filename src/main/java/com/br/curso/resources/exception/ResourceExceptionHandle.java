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

import com.br.curso.services.exception.AuthorizationExcption;
import com.br.curso.services.exception.DataIntegrityException;
import com.br.curso.services.exception.ObjectNotFoundException;


@ControllerAdvice
public class ResourceExceptionHandle {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> elementNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimeStamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMensagem(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);		
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> integrityViolation(DataIntegrityException e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimeStamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMensagem(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError error = new ValidationError();
		error.setTimeStamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMensagem("Erro de validação");
		error.setPath(request.getRequestURI());
		
		for(FieldError x:  e.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);		
	}
	
	@ExceptionHandler(AuthorizationExcption.class)
	public ResponseEntity<StandardError> authorization(AuthorizationExcption e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimeStamp(Instant.now());
		error.setStatus(HttpStatus.FORBIDDEN.value());
		error.setMensagem(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);		
	}
}
