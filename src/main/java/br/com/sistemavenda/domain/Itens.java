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
import javax.persistence.Table;

@Entity
@Table(name = "itens")
public class Itens implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Integer quantidade;
	@Column(nullable = false, scale = 2, precision = 10)
	private BigDecimal valorParcial;

	// chave estrangeira
	@ManyToOne(fetch = FetchType.EAGER) // muitos para um
	@JoinColumn(name = "id_produto", referencedColumnName = "id", nullable = false)
	private Produto produto;

	// chave estrangeira
	@ManyToOne(fetch = FetchType.EAGER) // muitos para um
	@JoinColumn(name = "id_vendas", referencedColumnName = "id", nullable = false)
	private Vendas vendas;

	public Itens() {
	}

	public Itens(Long id, Integer quantidade, BigDecimal valorParcial, Produto produto, Vendas vendas) {
		this.id = id;
		this.quantidade = quantidade;
		this.valorParcial = valorParcial;
		this.produto = produto;
		this.vendas = vendas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Vendas getVendas() {
		return vendas;
	}

	public void setVendas(Vendas vendas) {
		this.vendas = vendas;
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
		Itens other = (Itens) obj;
		return Objects.equals(id, other.id);
	}

}
