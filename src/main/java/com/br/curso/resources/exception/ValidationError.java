package com.br.curso.resources.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError() {
		super();
	}
	
	public ValidationError(Integer status, String mensagem, Instant timeStamp, String path) {
		super(status, mensagem, timeStamp, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	// acrescentar um erro de cada vez a lista
	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMessage(fieldName, messagem));
	}
	
	
	
	
}
