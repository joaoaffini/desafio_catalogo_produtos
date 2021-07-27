package com.compasso.uol.catalogo.produto.service.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4953521704994427790L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}