package br.com.sistemavenda.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "funcionario")
@NamedQueries({
	@NamedQuery(name = "Funcionario.listar", query = "SELECT funcionario from Funcionario funcionario"),
	@NamedQuery(name = "Funcionario.buscarPorId", query = "SELECT funcionario from Funcionario funcionario WHERE funcionario.id = :id")})
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Preencha o campo nome!")
	@Column(nullable = false, length = 150)
	private String nome;
	//@NotEmpty(message = "Preencha o campo CPF!")
	@CPF(message = "Preencha o campo CPF!")
	@Column(nullable = false, length = 20, unique = true)
	private String cpf;
	@NotEmpty(message = "Preencha o campo senha!")
	@Size(min = 5, max = 8, message = "Sua senha deve conter de 5 a 8 caracteres!")
	@Column(nullable = false, length = 50)
	private String senha;
	@NotEmpty(message = "Preencha o campo cargo!")
	@Column(nullable = false, length = 50)
	private String funcao;

	public Funcionario() {
	}

	public Funcionario(Long id, String nome, String cpf, String senha, String funcao) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.funcao = funcao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
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
		Funcionario other = (Funcionario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + ", funcao=" + funcao
				+ "]";
	}
	
	

}
