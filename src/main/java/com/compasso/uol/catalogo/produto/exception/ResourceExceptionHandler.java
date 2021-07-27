package com.compasso.uol.catalogo.produto.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.compasso.uol.catalogo.produto.service.exception.DuplicateProductException;
import com.compasso.uol.catalogo.produto.service.exception.ObjectNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationErrror err = new ValidationErrror(HttpStatus.BAD_REQUEST.value(), "Erro de validação", request.getRequestURI());

		e.getBindingResult().getFieldErrors().stream().forEach(fe -> {
			err.addError(fe.getField(), fe.getDefaultMessage());
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public StandardError objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		return new StandardError(HttpStatus.NOT_FOUND.value(), "Não encontrado");
		//return new StandardError(HttpStatus.NOT_FOUND.value(), "Não encontrado", request.getRequestURI());
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DuplicateProductException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public StandardError duplicatedProduct(ObjectNotFoundException e, HttpServletRequest request) {
		return new StandardError(HttpStatus.CONFLICT.value(), "Este produto já está cadastrado");
	}
}
