package com.br.curso.services;

import org.springframework.mail.SimpleMailMessage;

import com.br.curso.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
}
