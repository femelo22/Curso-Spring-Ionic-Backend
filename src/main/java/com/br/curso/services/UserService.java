package com.br.curso.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.br.curso.security.UserSS;

public class UserService {
	
	//retorna o usuário logado no sitema
	public static UserSS authenticated() {
		try {
			
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //pega usuário logado
			
		} catch (Exception e) {
			// se n achar retorna nulo
			return null;
		}
	}

}
