package com.jlstudents.crudpostagensusuario.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jlstudents.crudpostagensusuario.services.exceptions.AtualizacaoEntidadeInvalidaException;
import com.jlstudents.crudpostagensusuario.services.exceptions.BancoDadosException;
import com.jlstudents.crudpostagensusuario.services.exceptions.EntidadeNaoEncontradaException;
import com.jlstudents.crudpostagensusuario.services.exceptions.PostagemUsuarioGeralException;
import com.jlstudents.crudpostagensusuario.services.exceptions.RegistroNaoInformadoException;
import com.jlstudents.crudpostagensusuario.services.exceptions.UsuarioJaCadastradoException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<StandardError> entityNotFound(EntidadeNaoEncontradaException ex, HttpServletRequest request) {
		String message = "Entidade não encontrada.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError erroPersonalizado = new StandardError(Instant.now(), status.value(), message, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erroPersonalizado);
	}
	
	@ExceptionHandler(BancoDadosException.class)
	public ResponseEntity<StandardError> database(BancoDadosException ex, HttpServletRequest request) {
		String message = "Erro ao tentar executar a operação na base de dados.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erroPersonalizado = new StandardError(Instant.now(), status.value(), message, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erroPersonalizado);
	}
	
	@ExceptionHandler(PostagemUsuarioGeralException.class)
	public ResponseEntity<StandardError> genericError(PostagemUsuarioGeralException ex, HttpServletRequest request) {
		String message = "Erro ao tentar executar a requisição solicitada.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erroPersonalizado = new StandardError(Instant.now(), status.value(), message, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erroPersonalizado);
	}
	
	@ExceptionHandler(UsuarioJaCadastradoException.class)
	public ResponseEntity<StandardError> userAlreadyRegistered(UsuarioJaCadastradoException ex, HttpServletRequest request) {
		String message = "Não foi possível salvar o usuário na base de dados.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erroPersonalizado = new StandardError(Instant.now(), status.value(), message, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erroPersonalizado);
	}
	
	@ExceptionHandler(AtualizacaoEntidadeInvalidaException.class)
	public ResponseEntity<StandardError> updateInvalid(AtualizacaoEntidadeInvalidaException ex, HttpServletRequest request) {
		String message = "Não foi possível atualizar a entidade na base de dados.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erroPersonalizado = new StandardError(Instant.now(), status.value(), message, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erroPersonalizado);
	}
	
	@ExceptionHandler(RegistroNaoInformadoException.class)
	public ResponseEntity<StandardError> registerEntityNotFound(RegistroNaoInformadoException ex, HttpServletRequest request) {
		String message = "Registro de preenchimento obrigatório não informado.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erroPersonalizado = new StandardError(Instant.now(), status.value(), message, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erroPersonalizado);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> error(Exception ex, HttpServletRequest request) {
		String message = "Error.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erroPersonalizado = new StandardError(Instant.now(), status.value(), message, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erroPersonalizado);
	}
	
}
