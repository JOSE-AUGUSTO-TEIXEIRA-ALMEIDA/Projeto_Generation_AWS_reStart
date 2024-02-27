package com.jlstudents.crudpostagensusuario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlstudents.crudpostagensusuario.entities.Postagem;
import com.jlstudents.crudpostagensusuario.entities.Usuario;

public interface PostagemRepository extends JpaRepository<Postagem, Integer> {
	
}
