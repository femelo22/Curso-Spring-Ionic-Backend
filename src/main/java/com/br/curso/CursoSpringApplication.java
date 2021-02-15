package com.br.curso;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.curso.domain.Categoria;
import com.br.curso.domain.Cidade;
import com.br.curso.domain.Cliente;
import com.br.curso.domain.Endereco;
import com.br.curso.domain.Estado;
import com.br.curso.domain.Pagamento;
import com.br.curso.domain.PagamentoComBoleto;
import com.br.curso.domain.PagamentoComCartao;
import com.br.curso.domain.Pedido;
import com.br.curso.domain.Produto;
import com.br.curso.domain.enums.EstadoPagamento;
import com.br.curso.domain.enums.TipoCliente;
import com.br.curso.repositories.CategoriaRepository;
import com.br.curso.repositories.CidadeRepository;
import com.br.curso.repositories.ClienteRepository;
import com.br.curso.repositories.EnderecoRepository;
import com.br.curso.repositories.EstadoRepository;
import com.br.curso.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Marketing");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 110.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2= new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Juiz de Fora", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345678952", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("32999956148", "3299654158"));
		
		
		Endereco e1 = new Endereco(null, "Rua Coronel Vaz de Melo", "124", "201", "Bom Pastor", "36952102", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "5000", "Centro", "36125940", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("15/02/2021 14:55"),cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("15/02/2021 16:30"),cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/03/2021 00:00"), null);
		
	}

}
