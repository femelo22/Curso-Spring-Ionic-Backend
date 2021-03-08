package com.br.curso.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.br.curso.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	//adiciona uma data de vencimento do boleto
	public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date instanteDoPedido) {
		
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(instanteDoPedido);
		calendario.add(Calendar.DAY_OF_MONTH, 7);//acrescentando 7 dias a data atual
		
		pgto.setDataVencimento(calendario.getTime());
		
	}
}
