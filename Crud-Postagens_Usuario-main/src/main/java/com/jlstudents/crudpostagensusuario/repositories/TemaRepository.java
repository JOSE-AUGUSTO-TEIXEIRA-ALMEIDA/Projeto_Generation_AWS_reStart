package com.jlstudents.crudpostagensusuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlstudents.crudpostagensusuario.entities.Tema;

public interface TemaRepository extends JpaRepository<Tema, Integer> {

	Tema findByDescricao(String descricao);
	
}
