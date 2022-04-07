package br.com.sistemavenda.teste;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sistemavenda.dao.FornecedorDAO;
import br.com.sistemavenda.dao.FuncionarioDAO;
import br.com.sistemavenda.dao.VendasDAO;
import br.com.sistemavenda.domain.Fornecedor;
import br.com.sistemavenda.domain.Funcionario;
import br.com.sistemavenda.domain.Vendas;

public class VendasDaoTeste {
	
	private VendasDAO vendasDAO = new VendasDAO();
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

	@Test
	@Ignore
	public void salvar() throws Exception {
		
		Vendas vendas = new Vendas();
		vendas.setHorario(new Date());
		vendas.setValorTotal(new BigDecimal(880.00));
		
		Funcionario funcionario = funcionarioDAO.buscarPorId(3L);
		vendas.setFuncionario(funcionario);
		
		vendasDAO.salvar(vendas);
	}
	
	@Test
	@Ignore
	public void listar() throws Exception {
		List<Vendas> vendas = vendasDAO.listar();
		
		for (Vendas venda : vendas) {
			System.out.println("");
			System.out.println(venda);
			System.out.println("--------------------------------------------------------------");
		}
	}
	
	@Test
	@Ignore
	public void buscarPorId() throws Exception {
		Vendas vendas = vendasDAO.buscarPorId(1L);
		System.out.println(vendas);
	}
	
	@Test
	@Ignore
	public void excluir() throws Exception {
		Vendas vendas = vendasDAO.buscarPorId(5L);
		
		if(vendas != null) {
			vendasDAO.excluir(vendas);
			System.out.println("Venda excluída com sucesso.");
		} else {
			System.out.println("Não existe registro de vendas com esse ID na base de dados.");
		}
	}
	
	@Test
	@Ignore
	public void excluirPorId() throws Exception {
		
		vendasDAO.excluir(3L);
		
	}
	
	@Test
	@Ignore
	public void editar() throws Exception {
		
		Vendas vendas = vendasDAO.buscarPorId(2L);
		vendas.setHorario(new Date());
		vendas.setValorTotal(new BigDecimal(3352.25));
		
		Funcionario funcionario = funcionarioDAO.buscarPorId(1L);
		vendas.setFuncionario(funcionario);
		
		vendasDAO.editar(vendas);
		
	}
	
	
}
