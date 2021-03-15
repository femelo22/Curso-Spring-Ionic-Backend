package com.br.curso.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.br.curso.domain.Cliente;
import com.br.curso.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);// para enviar o email plano (sem html) usamos o SimpleMailMessage
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);// para enviar o template html do email
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
