package com.br.curso.resources.exception;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Integer status;
	public String mensagem;
	public Instant timeStamp;
	public String path;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Instant getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Instant timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	

	
	
	
}
