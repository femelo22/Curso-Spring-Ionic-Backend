package com.br.curso.resources.exception;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Integer status;
	public String msg;
	public Instant timeStamp;
	public String path;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
