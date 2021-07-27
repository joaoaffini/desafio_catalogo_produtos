package com.compasso.uol.catalogo.produto.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrror extends StandardError {

	private static final long serialVersionUID = -5439308350141515957L;
	
	private List<FieldMessage> errors = new ArrayList<>(); 
	
	public ValidationErrror(Integer status, String error, String path) {
		super(status, error);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}