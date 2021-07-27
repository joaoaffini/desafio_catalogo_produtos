package com.compasso.uol.catalogo.produto.exception;

import java.io.Serializable;

import lombok.Data;

@Data
public class StandardError implements Serializable {
	
	private static final long serialVersionUID = -7388325696045660793L;
	
	private Integer status_code;
	private String message;
	
	public StandardError(Integer status, String message) {
		super();
		this.status_code = status;
		this.message = message;
	}
	
}
