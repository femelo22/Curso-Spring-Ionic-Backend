package com.br.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Cliente;
import com.br.curso.repositories.ClienteRepository;
import com.br.curso.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //buscando o usuario do security pelo EMAIL

		Cliente cli = repo.findByEmail(email);
		
		if(cli == null) {
			throw new UsernameNotFoundException(email);//excessao informando o email
		}
		
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}

}
