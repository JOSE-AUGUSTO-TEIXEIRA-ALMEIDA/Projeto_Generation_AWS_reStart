package com.jlstudents.crudpostagensusuario.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jlstudents.crudpostagensusuario.entities.Postagem;
import com.jlstudents.crudpostagensusuario.entities.Tema;
import com.jlstudents.crudpostagensusuario.entities.Usuario;
import com.jlstudents.crudpostagensusuario.repositories.PostagemRepository;
import com.jlstudents.crudpostagensusuario.services.exceptions.AtualizacaoEntidadeInvalidaException;
import com.jlstudents.crudpostagensusuario.services.exceptions.BancoDadosException;
import com.jlstudents.crudpostagensusuario.services.exceptions.EntidadeNaoEncontradaException;
import com.jlstudents.crudpostagensusuario.services.exceptions.PostagemUsuarioGeralException;
import com.jlstudents.crudpostagensusuario.services.exceptions.RegistroNaoInformadoException;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TemaService temaService;
	
	public List<Postagem> findAll() {
		List<Postagem> listaPostagens = postagemRepository.findAll();
		if (listaPostagens == null || listaPostagens.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Nenhuma postagem foi encontrado na base de dados do sistema.");
		}
		listaPostagens.sort((obj1, obj2) -> Integer.compare(obj1.getId(), obj2.getId()));
		return listaPostagens;
	}
	
	public Postagem findById(Integer id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);		
		return postagem.orElseThrow(() -> new EntidadeNaoEncontradaException("Postagem não se encontra cadastrado na base de dados do sistema.", id));
	}
	
	public Postagem insert(Postagem postagem) {
		try {
			validatePost(postagem);
			return postagemRepository.save(postagem);
		} catch (Exception e) {
			throw new BancoDadosException("Erro ao tentar registrar postagem na base de dados. " + e.getMessage());
		}
	}
	
	private void validatePost(Postagem postagem) {
		validateUser(postagem);
		validateTema(postagem);
	}
	
	private void validateUser(Postagem postagem) {
		Usuario usuario = usuarioService.findByEmail(postagem.getUsuario().getEmail());
		if (usuario == null) {
			usuario = usuarioService.insert(postagem.getUsuario());
		}
		postagem.setUsuario(usuario);
	}

	private void validateTema(Postagem postagem) {
		Tema tema = temaService.findByDescricao(postagem.getTema().getDescricao());
		if (tema == null) {
			tema = temaService.insert(postagem.getTema());
		}
		postagem.setTema(tema);
	}

	public void deleteById(Integer id) {
		try {
			postagemRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BancoDadosException("Não foi possível excluir a postagem de Id: " + id + " da base de dados. " + e.getMessage());
		} catch (Exception e) {
			throw new PostagemUsuarioGeralException("Não foi possível excluir a postagem de Id: " + id + " da base de dados. " + e.getMessage());
		}
	}
	
	public Postagem update(Integer id, Postagem postagem) {
		try {
			if (id == null) {
				throw new RegistroNaoInformadoException("Não foi informado um Id da postagem para atualização do registro.");
			}
			Postagem entidadeMonitorada = postagemRepository.getReferenceById(id);
			updateData(entidadeMonitorada, postagem);
			return postagemRepository.save(entidadeMonitorada);
		} catch (RegistroNaoInformadoException ex) {
			throw ex;
		} catch (EntidadeNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException("Postagem não se encontra cadastrado na base de dados do sistema.", id);
		} catch (Exception e) {
			throw new PostagemUsuarioGeralException("Não foi possível atualizar os dados da postagem de Id: " + id + ". " + e.getMessage());
		}
	}

	private void updateData(Postagem entidadeMonitorada, Postagem postagemObjeto) {
		validateUpdate(entidadeMonitorada, postagemObjeto);
		entidadeMonitorada.setTitulo(postagemObjeto.getTitulo());
		entidadeMonitorada.setTexto(postagemObjeto.getTexto());
		entidadeMonitorada.setData(new Date());
		entidadeMonitorada.setTema(postagemObjeto.getTema());
	}
	
	private void validateUpdate(Postagem entidadeMonitorada, Postagem postagemObjeto) {
		if (!entidadeMonitorada.getUsuario().equals(postagemObjeto.getUsuario())) {
			throw new AtualizacaoEntidadeInvalidaException("O usuário da postagem não pode ser alterado");
		}
	}
	
}
