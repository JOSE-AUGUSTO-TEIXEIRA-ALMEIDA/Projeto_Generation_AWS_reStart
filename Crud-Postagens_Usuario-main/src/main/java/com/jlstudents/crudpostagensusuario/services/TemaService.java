package com.jlstudents.crudpostagensusuario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jlstudents.crudpostagensusuario.entities.Tema;
import com.jlstudents.crudpostagensusuario.repositories.TemaRepository;
import com.jlstudents.crudpostagensusuario.services.exceptions.BancoDadosException;
import com.jlstudents.crudpostagensusuario.services.exceptions.EntidadeNaoEncontradaException;
import com.jlstudents.crudpostagensusuario.services.exceptions.PostagemUsuarioGeralException;
import com.jlstudents.crudpostagensusuario.services.exceptions.RegistroNaoInformadoException;
import com.jlstudents.crudpostagensusuario.services.exceptions.UsuarioJaCadastradoException;

@Service
public class TemaService {

	@Autowired
	private TemaRepository temaRepository;
	
	public List<Tema> findAll() {
		List<Tema> listaTemas = temaRepository.findAll();
		if (listaTemas == null || listaTemas.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Nenhum tema foi encontrado na base de dados do sistema.");
		}
		listaTemas.sort((obj1, obj2) -> Integer.compare(obj1.getId(), obj2.getId()));
		return listaTemas;
	}
	
	public Tema findById(Integer id) {
		Optional<Tema> tema = temaRepository.findById(id);		
		return tema.orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não se encontra cadastrado na base de dados do sistema.", id));
	}
	
	public Tema findByDescricao(String descricao) {
		return temaRepository.findByDescricao(descricao);
	}
	
	public Tema insert(Tema tema) {
		try {
			return temaRepository.save(tema);
		} catch (Exception e) {
			throw new BancoDadosException("Erro ao cadastrar usuário na base de dados. " + e.getMessage());
		}
	}
	
	public void deleteById(Integer id) {
		try {
			temaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BancoDadosException("Não foi possível excluir o tema de Id: " + id + " da base de dados. " + e.getMessage());
		} catch (Exception e) {
			throw new PostagemUsuarioGeralException("Não foi possível excluir o tema de Id: " + id + " da base de dados. " + e.getMessage());
		}
	}
	
	public Tema update(Integer id, Tema tema) {
		try {
			validateUpdate(id, tema);
			Tema entidadeMonitorada = temaRepository.getReferenceById(id);
			updateData(entidadeMonitorada, tema);
			return temaRepository.save(entidadeMonitorada);
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
	
	private void validateUpdate(Integer id, Tema tema) {
		if (id == null) {
			throw new RegistroNaoInformadoException("Não foi informado um Id do tema para atualização do registro.");
		}
	}

	private void updateData(Tema entidadeMonitada, Tema temaObjeto) {
		entidadeMonitada.setDescricao(temaObjeto.getDescricao());
	}
	
}
