package br.com.sistemavenda.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sistemavenda.dao.FornecedorDAO;
import br.com.sistemavenda.dao.ProdutoDAO;
import br.com.sistemavenda.domain.Fornecedor;
import br.com.sistemavenda.domain.Produto;

public class ProdutoDaoTeste {
	
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private FornecedorDAO fornecedorDAO = new FornecedorDAO();

	@Test
	@Ignore
	public void salvar() throws Exception {
		
		//primeiro vou pesquisar o fornecedor
		Fornecedor fornecedor = fornecedorDAO.buscarPorId(2L);
		
		Produto produto = new Produto();
		produto.setDescricao("box gokei grande".toUpperCase());
		produto.setPreco(new BigDecimal(155.25));
		produto.setQuantidade(200);
		produto.setFornecedor(fornecedor);
		
		produtoDAO.salvar(produto);
	}
	
	@Test
	@Ignore
	public void listar() throws Exception {
		
		List<Produto> produtos = produtoDAO.listar();
		
		for (Produto produto : produtos) {
			System.out.println(produto);
			System.out.println("------------------------------------------------------------------");
		}
	}
	
	@Test
	@Ignore
	public void buscarPorId() throws Exception {
		
		Produto produto = produtoDAO.buscarPorId(4L);
		System.out.println("");
		System.out.println(produto);
		System.out.println("-----------------------------------------");
	}
	
	@Test
	@Ignore
	public void excluir() throws Exception {
		
		Produto produto = produtoDAO.buscarPorId(5L);
		
		if(produto != null) {
			produtoDAO.excluir(produto);
		} else {
			System.out.println("Produto não existe na base de dados.");
		}
	}
	
	@Test
	@Ignore
	public void excluirPorId() throws Exception {
		
		produtoDAO.excluirPorId(1L);
		
	}
	
	@Test
	@Ignore
	public void editar() throws Exception {
		
		Produto produto = new Produto();
		produto.setId(2L);
		produto.setDescricao("novo teste de cebola crispy editado".toUpperCase());
		produto.setPreco(new BigDecimal(121.99));
		produto.setQuantidade(50);
		
		Fornecedor fornecedor = fornecedorDAO.buscarPorId(1L);
		produto.setFornecedor(fornecedor);
		
		produtoDAO.editar(produto);
		
	}
	
	
}
