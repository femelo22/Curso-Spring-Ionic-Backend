package com.br.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Cliente;
import com.br.curso.repositories.ClienteRepository;
import com.br.curso.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) {
		
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
		
		return cliente;
		
	}
}
