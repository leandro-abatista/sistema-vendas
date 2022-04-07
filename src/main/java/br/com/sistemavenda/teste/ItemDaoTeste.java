package br.com.sistemavenda.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sistemavenda.dao.ItemDAO;
import br.com.sistemavenda.dao.ProdutoDAO;
import br.com.sistemavenda.dao.VendasDAO;
import br.com.sistemavenda.domain.Item;
import br.com.sistemavenda.domain.Produto;
import br.com.sistemavenda.domain.Vendas;

public class ItemDaoTeste {
	
	private ItemDAO ItemDAO = new ItemDAO();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private VendasDAO vendasDAO = new VendasDAO();

	@Test
	@Ignore
	public void salvar() throws Exception {
		
		Produto produto = produtoDAO.buscarPorId(3L);
		Vendas venda = vendasDAO.buscarPorId(2L);
		
		Item item = new Item();
		item.setProduto(produto);
		item.setVendas(venda);
		item.setQuantidade(150);
		item.setValorParcial(new BigDecimal(23.99));
		
		ItemDAO.salvar(item);
	}
	
	@Test
	@Ignore
	public void listar() throws Exception {
		List<Item> itens = ItemDAO.listar();
		
		for (Item item : itens) {
			System.out.println("");
			System.out.println(item);
			System.out.println("--------------------------------------------------------------");
		}
	}
	
	@Test
	@Ignore
	public void buscarPorId() throws Exception {
		Item item = ItemDAO.buscarPorId(1L);
		System.out.println(item);
	}
	
	@Test
	@Ignore
	public void excluir() throws Exception {
		Item item = ItemDAO.buscarPorId(3L);
		
		if(item != null) {
			ItemDAO.excluir(item);
			System.out.println("Item excluído com sucesso.");
		} else {
			System.out.println("Não existe registro de Item com esse ID na base de dados.");
		}
	}
	
	@Test
	@Ignore
	public void excluirPorId() throws Exception {
		
		ItemDAO.excluir(3L);
		
	}
	
	@Test
	@Ignore
	public void editar() throws Exception {
		
		Item item = ItemDAO.buscarPorId(2L);
		
		Produto produto = produtoDAO.buscarPorId(2L);
		Vendas venda = vendasDAO.buscarPorId(1L);
		
		item.setProduto(produto);
		item.setVendas(venda);
		item.setQuantidade(100);
		item.setValorParcial(new BigDecimal(33.99));
		
		ItemDAO.editar(item);
	}
	
	
}
