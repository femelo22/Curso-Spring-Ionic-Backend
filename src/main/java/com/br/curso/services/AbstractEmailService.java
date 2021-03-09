package com.br.curso.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.br.curso.domain.Pedido;


public abstract class AbstractEmailService implements EmailService{

	//processar o template para retornar o html em forma de String
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
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
		return sm;
	}
	
	
	// I M P O R T A N T E
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context(); 
		context.setVariable("pedido", obj); // DEFINE O APELIDO QUE VAI SER USADO NO TEMPLATE HTML (** IMPORTANTE **)
		return templateEngine.process("email/confirmacaoPedido", context); //Processa o template e injeta o obj pedido lá dentro
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mm);
		} catch (Exception e) {// tenta mandar o email html, se der erro, envia o texto plano
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido Confirmado!   Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		
		return mimeMessage;
	}
	
}
