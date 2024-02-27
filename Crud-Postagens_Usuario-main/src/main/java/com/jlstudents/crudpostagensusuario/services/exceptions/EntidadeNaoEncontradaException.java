package com.jlstudents.crudpostagensusuario.services.exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(final String message) {
		super(message);
	}
	
	public EntidadeNaoEncontradaException(final Integer id) {
		super("Entidade não foi encontrada na base de dados. [Id + - " + id + "]");
	}
	
	public EntidadeNaoEncontradaException(final String message, final Integer id) {
		super(message + " [Id: " + id + "]");
	}
	
}
