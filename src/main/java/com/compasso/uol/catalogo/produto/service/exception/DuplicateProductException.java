package com.compasso.uol.catalogo.produto.service.exception;

public class DuplicateProductException extends RuntimeException {


	private static final long serialVersionUID = -3137270198514362227L;
	
	public DuplicateProductException(String msg) {
		super(msg);
	}
	
	public DuplicateProductException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
