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

import com.jlstudents.crudpostagensusuario.entities.Tema;
import com.jlstudents.crudpostagensusuario.services.TemaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/temas")
public class TemaController {

	@Autowired
	private TemaService temaService;

	@GetMapping
	@Operation(summary = "Obtém todos os temas de postagens cadastradas no sistema.")
	public ResponseEntity<List<Tema>> findAll() {
		List<Tema> temas = temaService.findAll();
		return ResponseEntity.ok().body(temas);
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Obtém um tema de postagem pelo seu Id cadastrado na base de dados do sistema.")
	public ResponseEntity<Tema> findById(@PathVariable Integer id) {
		Tema tema = temaService.findById(id);
		return ResponseEntity.ok().body(tema);
	}

	@PostMapping
	@Operation(summary = "Realiza o cadastro de um novo tema de postagem no sistema.")
	public ResponseEntity<Tema> insert(@RequestBody Tema novoTema) {
		Tema tema = temaService.insert(novoTema);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(tema.getId()).toUri();
		return ResponseEntity.created(uri).body(tema);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Realiza a exclusão de um tema do sistema pelo seu Id.")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		temaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@Operation(summary = "Atualiza dados do cadastro de um tema de postagem já existente na base de dados do sistema.")
	public ResponseEntity<Tema> update(@RequestBody Tema temaPraAtualizar, @PathVariable Integer id) {
		Tema tema = temaService.update(id, temaPraAtualizar);
		return ResponseEntity.ok().body(tema);
	}

}
