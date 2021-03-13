package com.br.curso.services.exception;

public class AuthorizationExcption extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AuthorizationExcption(String msg) {
		super(msg);
	}
	
	public AuthorizationExcption(String msg, Throwable cause) {
		super(msg, cause);
	}
}
