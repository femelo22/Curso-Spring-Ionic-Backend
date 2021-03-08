package com.br.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Cliente;
import com.br.curso.domain.Pedido;
import com.br.curso.repositories.ClienteRepository;
import com.br.curso.repositories.PedidoRepository;
import com.br.curso.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido findById(Integer id) {
		
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
		
		return pedido;
	}
}
