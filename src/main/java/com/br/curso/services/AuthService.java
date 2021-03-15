package com.br.curso.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Cliente;
import com.br.curso.repositories.ClienteRepository;
import com.br.curso.services.exception.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	

	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado.");
		}
		
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		emailService.sendNewPasswordEmail(cliente, newPass);
		
	}


	private String newPassword() {
		UUID uuid = UUID.randomUUID();
		String newPass = uuid.toString().substring(0,8);
		
		return newPass;
	}
}
