package com.jlstudents.crudpostagensusuario.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = "Usuario" )
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Schema(hidden = true)
    private Integer id;
	
	@NotNull(message = "O campo nome é obrigatório")
	@Size(min = 3, max = 100, message = "O nome deve ser ter no mínimo 3 e no máximo 100 caracteres")
    @Column(name = "nome", nullable = false)
	private String nome;
	
    @NotNull(message = "O campo email é obrigatório")
    @Email(message = "Email informado está inválido, favor informe um email corretamente")
    @Column(name = "email", nullable = false, unique = true)
	private String email;
    
    @JsonIgnore
    @Column(name = "foto", nullable = true, columnDefinition = "text")
    private String foto;
    
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Postagem> postagens = new ArrayList<>();
	    
    public Usuario() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
	
}
