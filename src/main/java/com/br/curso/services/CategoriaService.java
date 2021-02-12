package com.br.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.curso.domain.Categoria;
import com.br.curso.repositories.CategoriaRepository;
import com.br.curso.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly = true)
	public Categoria buscarPorId(Integer id){
		
		Categoria categoria = this.categoriaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada"));
		
		return categoria;
	}
	
	
	
	

}
