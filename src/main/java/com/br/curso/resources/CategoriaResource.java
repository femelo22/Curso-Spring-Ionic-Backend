package com.br.curso.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.curso.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {
		
		Categoria c1 = new Categoria(1, "Informatica");
		Categoria c2 = new Categoria(1, "Escritorio");
		
		List<Categoria> categorias = new ArrayList<>();
		categorias.add(c1);
		categorias.add(c2);
		
		return categorias;
	}
	
	
}