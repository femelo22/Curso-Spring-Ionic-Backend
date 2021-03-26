package com.br.curso.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.curso.domain.Categoria;
import com.br.curso.domain.Cliente;
import com.br.curso.domain.enums.Perfil;
import com.br.curso.dto.CategoriaDTO;
import com.br.curso.dto.ClienteDTO;
import com.br.curso.dto.ClienteNewDTO;
import com.br.curso.security.UserSS;
import com.br.curso.services.ClienteService;
import com.br.curso.services.UserService;
import com.br.curso.services.exception.AuthorizationException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id){
		
		UserSS user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado.");
		}
		
		Cliente cliente = clienteService.findById(id);
		
		return ResponseEntity.ok().body(cliente);		
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
		Cliente cliente = clienteService.fromDTO(objDto);//criando objeto Categoria a partir de uma CategoriaDto
		
		cliente = clienteService.insert(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
		Cliente obj = clienteService.fromDTO(objDto);
		obj.setId(id);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		clienteService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); // trasforma uma lista em outra( no caso uma lista de categorias em categorias dto)
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/email")
	public ResponseEntity<Cliente> findByEmail(@RequestParam(value = "value") String email){
		Cliente obj = clienteService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction){
		
		Page<Cliente> list = clienteService.findPage(page, linesPerPage,direction, orderBy);
		
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj)); //Page já é java 11 complience
		
		return ResponseEntity.ok().body(listDto);
	}
	
	
}
