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

import com.jlstudents.crudpostagensusuario.entities.Postagem;
import com.jlstudents.crudpostagensusuario.services.PostagemService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/postagens")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;

	@GetMapping
	@Operation(summary = "Obtém todas as postagens realizadas no sistema.")
	public ResponseEntity<List<Postagem>> findAll() {
		List<Postagem> postagens = postagemService.findAll();
		return ResponseEntity.ok().body(postagens);
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Obtém uma postagem pelo seu Id cadastrado na base de dados do sistema.")
	public ResponseEntity<Postagem> findById(@PathVariable Integer id) {
		Postagem postagem = postagemService.findById(id);
		return ResponseEntity.ok().body(postagem);
	}
	
	@PostMapping
	@Operation(summary = "Realiza o cadastro de um nova postagem no sistema.")
	public ResponseEntity<Postagem> insert(@RequestBody Postagem novaPostagem) {
		Postagem postagem = postagemService.insert(novaPostagem);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(postagem.getId()).toUri();
		return ResponseEntity.created(uri).body(postagem);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Realiza a exclusão de uma postagem do sistema pelo seu Id.")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		postagemService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@Operation(summary = "Atualiza dados de uma postagem já existente na base de dados do sistema.")
	public ResponseEntity<Postagem> update(@RequestBody Postagem postagemPraAtualizar, @PathVariable Integer id) {
		Postagem postagem = postagemService.update(id, postagemPraAtualizar);
		return ResponseEntity.ok().body(postagem);
	}

}
