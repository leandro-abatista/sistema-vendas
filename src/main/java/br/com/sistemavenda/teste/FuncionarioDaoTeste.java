package br.com.sistemavenda.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sistemavenda.dao.FornecedorDAO;
import br.com.sistemavenda.dao.FuncionarioDAO;
import br.com.sistemavenda.domain.Fornecedor;
import br.com.sistemavenda.domain.Funcionario;

public class FuncionarioDaoTeste {
	
	private FuncionarioDAO dao = new FuncionarioDAO();

	@Test
	@Ignore
	public void salvar() throws Exception {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("mariana alves brito".toUpperCase());
		funcionario.setCpf("454465446");
		funcionario.setSenha("1321");
		funcionario.setFuncao("auxiliar de escritório".toUpperCase());
		
		dao.salvar(funcionario);
	}
	
	@Test
	@Ignore
	public void listar() throws Exception {
		
		List<Funcionario> funcionarios = dao.listar();
		
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
			System.out.println("------------------------------------------------------");
		}
	}
	
	@Test
	@Ignore
	public void buscarPorId() throws Exception {
		
		Funcionario funcionario = dao.buscarPorId(2L);
		System.out.println(funcionario);
	}
	
	@Test
	@Ignore
	public void excluir() throws Exception {
		
		Funcionario funcionario = dao.buscarPorId(3L);
		
		if(funcionario != null) {
			dao.excluir(funcionario);
			System.out.println("Funcionário excluído com sucesso!");
		} else {
			System.out.println("Funcionário não existe na base de dados.");
		}
	}
	
	@Test
	@Ignore
	public void excluirPorId() throws Exception {
		dao.excluirPorId(4L);
		
	}
	
	@Test
	@Ignore
	public void editar() throws Exception {
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(1L);
		funcionario.setNome("joão alves costa".toUpperCase());
		funcionario.setCpf("454564564");
		funcionario.setSenha("1231");
		funcionario.setFuncao("Administrador".toUpperCase());
		
		dao.editar(funcionario);
		
	}
}
