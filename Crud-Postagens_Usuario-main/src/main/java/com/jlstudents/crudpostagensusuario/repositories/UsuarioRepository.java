package com.jlstudents.crudpostagensusuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlstudents.crudpostagensusuario.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByEmail(String email);
	
}
