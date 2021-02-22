package com.br.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Cliente;
import com.br.curso.domain.Cliente;
import com.br.curso.dto.ClienteDTO;
import com.br.curso.repositories.ClienteRepository;
import com.br.curso.services.exception.DataIntegrityException;
import com.br.curso.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findById(Integer id) {
		
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
		
		return cliente;
		
	}
	
	public Cliente save(Cliente obj) {
		obj.setId(null);
		return clienteRepository.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}
	
	public void deleteById(Integer id) {
		findById(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possivel excluir cliente");
		}
	}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage,String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
