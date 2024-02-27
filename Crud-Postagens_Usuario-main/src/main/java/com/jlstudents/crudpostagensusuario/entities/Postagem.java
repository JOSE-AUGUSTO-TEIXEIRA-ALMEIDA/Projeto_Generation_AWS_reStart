package com.jlstudents.crudpostagensusuario.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Postagem")
public class Postagem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Schema(hidden = true)
    private Integer id;
	
	@NotNull(message = "É obrigatório informar o título da postagem")
	@Size(min = 5, message = "O título da postagem deve ter no mínimo 5 caracteres")
    @Column(name = "titulo", nullable = false)
    private String titulo;
	
	@NotNull(message = "É obrigatório informar o texto da postagem")
	@Size(min = 10, message = "O texto da postagem deve ter no mínimo 10 caracteres")
    @Column(name = "texto", nullable = false)
	private String texto;
	
	@JsonIgnore
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date data = new Date();
	
    @NotNull(message = "Não é possível realizar postagem sem usuário informado")
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
	private Usuario usuario;
	
    @NotNull(message = "Não é possível realizar postagem sem tema associado")
    @ManyToOne
    @JoinColumn(name = "tema_id", referencedColumnName = "id", nullable = false)
	private Tema tema;
    
    public Postagem() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postagem other = (Postagem) obj;
		return Objects.equals(id, other.id);
	}
	
}
