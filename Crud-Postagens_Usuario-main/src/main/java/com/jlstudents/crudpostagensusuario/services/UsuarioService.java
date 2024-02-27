package com.jlstudents.crudpostagensusuario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jlstudents.crudpostagensusuario.entities.Usuario;
import com.jlstudents.crudpostagensusuario.repositories.UsuarioRepository;
import com.jlstudents.crudpostagensusuario.services.exceptions.BancoDadosException;
import com.jlstudents.crudpostagensusuario.services.exceptions.EntidadeNaoEncontradaException;
import com.jlstudents.crudpostagensusuario.services.exceptions.PostagemUsuarioGeralException;
import com.jlstudents.crudpostagensusuario.services.exceptions.RegistroNaoInformadoException;
import com.jlstudents.crudpostagensusuario.services.exceptions.UsuarioJaCadastradoException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll() {
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		if (listaUsuarios == null || listaUsuarios.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Nenhum usuário foi encontrado na base de dados do sistema.");
		}
		listaUsuarios.sort((usuario1, usuario2) -> Integer.compare(usuario1.getId(), usuario2.getId()));
		return listaUsuarios;
	}
	
	public Usuario findById(Integer id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);		
		return usuario.orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não se encontra cadastrado na base de dados do sistema.", id));
	}
	
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Usuario insert(Usuario usuario) {
		try {
			validateUser(usuario);
			return usuarioRepository.save(usuario);
		} catch (UsuarioJaCadastradoException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BancoDadosException("Erro ao cadastrar usuário na base de dados. " + e.getMessage());
		}
	}
	
	private void validateUser(Usuario novoUsuario) {
		Usuario usuario = usuarioRepository.findByEmail(novoUsuario.getEmail());
		if (usuario != null) {
			throw new UsuarioJaCadastradoException("Já existe usuário cadastrado com email [" + usuario.getEmail() + "]. ");
		}
	}

	public void deleteById(Integer id) {
		try {
			usuarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BancoDadosException("Não foi possível excluir o usuário de Id: " + id + " da base de dados. " + e.getMessage());
		} catch (Exception e) {
			throw new PostagemUsuarioGeralException("Não foi possível excluir o usuário de Id: " + id + " da base de dados. " + e.getMessage());
		}
	}
	
	public Usuario update(Integer id, Usuario usuario) {
		try {
			validateUpdate(id, usuario);
			Usuario entidadeMonitorada = usuarioRepository.getReferenceById(id);
			updateData(entidadeMonitorada, usuario);
			return usuarioRepository.save(entidadeMonitorada);
		} catch (RegistroNaoInformadoException ex) {
			throw ex;
		} catch (UsuarioJaCadastradoException ex) {
			throw ex;
		} catch (EntidadeNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException("Usuário não se encontra cadastrado na base de dados do sistema.", id);
		} catch (Exception e) {
			throw new PostagemUsuarioGeralException("Não foi possível atualizar os dados do usuário de Id: " + id + ". " + e.getMessage());
		}
	}
	
	private void validateUpdate(Integer id, Usuario usuario) {
		if (id == null) {
			throw new RegistroNaoInformadoException("Não foi informado um Id do usuario para atualização do registro.");
		}
	}
	
	private void updateData(Usuario entidadeMonitorada, Usuario usuarioObjeto) {
		validateUser(entidadeMonitorada);
		entidadeMonitorada.setNome(usuarioObjeto.getNome());
		entidadeMonitorada.setEmail(usuarioObjeto.getEmail());
		entidadeMonitorada.setFoto(usuarioObjeto.getFoto());
	}
		
}
