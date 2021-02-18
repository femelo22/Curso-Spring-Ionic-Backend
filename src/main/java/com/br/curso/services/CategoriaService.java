package com.br.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.curso.domain.Categoria;
import com.br.curso.repositories.CategoriaRepository;
import com.br.curso.services.exception.DataIntegrityException;
import com.br.curso.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly = true)
	public Categoria findById(Integer id){
		
		Categoria categoria = this.categoriaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada"));
		
		return categoria;
	}
	
	public Categoria save(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return categoriaRepository.save(obj);
	}
	
	public void deleteById(Integer id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}

}
