package br.com.sistemavenda.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sistemavenda.dao.FornecedorDAO;
import br.com.sistemavenda.domain.Fornecedor;

public class FornecedorDaoTeste {

	@Test
	@Ignore
	public void salvar() throws Exception {
		Fornecedor f = new Fornecedor();
		f.setRazaosocial("patlog de alimentos ltda");
		f.setCnpj("045.123.600/0001-22");
		
		FornecedorDAO dao = new FornecedorDAO();
		dao.salvar(f);
	}
	
	@Test
	@Ignore
	public void listar() throws Exception {
		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> fornecedores = dao.listar();
		
		for (Fornecedor fornecedor : fornecedores) {
			System.out.println(fornecedor);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorId() throws Exception {
		FornecedorDAO dao = new FornecedorDAO();
		Fornecedor f = dao.buscarPorId(4L);
		System.out.println(f);
	}
	
	@Test
	@Ignore
	public void excluir() throws Exception {
		FornecedorDAO dao = new FornecedorDAO();
		Fornecedor f = dao.buscarPorId(4L);
		
		if(f != null) {
			dao.excluir(f);
		} else {
			System.out.println("Fornecedor não existe na base de dados.");
		}
	}
	
	@Test
	@Ignore
	public void excluirPorId() throws Exception {
		FornecedorDAO dao = new FornecedorDAO();
		dao.excluir(4L);
		
	}
	
	@Test
	@Ignore
	public void editar() throws Exception {
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(3L);
		fornecedor.setRazaosocial("Novo teste de editar");
		fornecedor.setCnpj("123111112311313");
		
		FornecedorDAO dao = new FornecedorDAO();
		dao.editar(fornecedor);
		
	}
	
	
}
