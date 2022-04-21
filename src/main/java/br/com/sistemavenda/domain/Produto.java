package br.com.sistemavenda.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "produto")
@NamedQueries({
	@NamedQuery(name = "Produto.listar", query = "SELECT produto from Produto produto"),
	@NamedQuery(name = "Produto.buscarPorId", query = "SELECT produto from Produto produto WHERE produto.id = :id")})
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Preencha o campo descrição do produto!")
	@Column(nullable = false, length = 150)
	private String descricao;
	@NotNull(message = "Preencha o campo preço!")
	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0.00")
	@Column(nullable = false, scale = 2, precision = 10)
	private BigDecimal preco;
	@NotNull(message = "Preencha o campo quantidade!")
	@Min(value = 1, message = "A quantidade não pode ser menor que 1!")
	@Column(nullable = false)
	private Integer quantidade;

	// chave estrangeira
	@NotNull(message = "Preencha o campo fornecedor!")
	@ManyToOne(fetch = FetchType.EAGER) // muitos para um
	@JoinColumn(name = "id_fornecedor", referencedColumnName = "id", nullable = false)
	private Fornecedor fornecedor;

	public Produto() {
	}

	public Produto(Long id, String descricao, BigDecimal preco, Integer quantidade, Fornecedor fornecedor) {
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		this.fornecedor = fornecedor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", preco=" + preco + ", quantidade=" + quantidade
				+ ", fornecedor=" + fornecedor.getRazaosocial() + "]";
	}
	
	

}
