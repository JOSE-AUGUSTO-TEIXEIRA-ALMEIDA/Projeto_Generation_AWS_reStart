package com.jlstudents.crudpostagensusuario.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jlstudents.crudpostagensusuario.entities.Usuario;
import com.jlstudents.crudpostagensusuario.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	@Operation(summary = "Obtém todos os usuários cadastrados no sistema.")
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> usuarios = usuarioService.findAll();
		return ResponseEntity.ok().body(usuarios);
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Obtém um estudante pelo seu Id cadastrado na base de dados do sistema.")
	public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
		Usuario usuario = usuarioService.findById(id);
		return ResponseEntity.ok().body(usuario);
	}

	@PostMapping
	@Operation(summary = "Realiza o cadastro de um novo usuário no sistema.")
	public ResponseEntity<Usuario> insert(@RequestBody Usuario novoUsuario) {
		Usuario usuario = usuarioService.insert(novoUsuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Realiza a exclusão de um usuário do sistema pelo seu Id.")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		usuarioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@Operation(summary = "Atualiza dados do cadastro de um usuário já existente na base de dados do sistema.")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuarioPraAtualizar, @PathVariable Integer id) {
		Usuario usuario = usuarioService.update(id, usuarioPraAtualizar);
		return ResponseEntity.ok().body(usuario);
	}

}
