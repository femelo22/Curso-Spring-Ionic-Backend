package com.br.curso.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.br.curso.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());//quem vai receber (nosso cliente q fez o pedido)
		sm.setFrom(sender);//quem está enviando
		sm.setSubject("Pedido confirmado!  Código: " + pedido.getId());//titulo do email
		sm.setSentDate(new Date(System.currentTimeMillis()));//setando a data do nosso servidor
		sm.setText(pedido.toString());// nossa mensagem criado no toString
		return null;
	}
	
	
	
}
