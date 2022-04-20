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

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "fornecedor")
@NamedQueries({
	@NamedQuery(name = "Fornecedor.listar", query = "SELECT fornecedor from Fornecedor fornecedor"),
	@NamedQuery(name = "Fornecedor.buscarPorId", query = "SELECT fornecedor from Fornecedor fornecedor WHERE fornecedor.id = :id")})
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Preencha o campo Razão Social!")
	@Column(name = "razaosocial", nullable = false, length = 150)
	private String razaosocial;

	@CNPJ(message = "Preencha o campo CNPJ!")
	@Column(name = "cnpj", nullable = false, length = 25)
	private String cnpj;

	public Fornecedor() {
	}

	public Fornecedor(Long id, String razaosocial, String cnpj) {
		this.id = id;
		this.razaosocial = razaosocial;
		this.cnpj = cnpj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", razaosocial=" + razaosocial + ", cnpj=" + cnpj + "]";
	}
	
	

}
