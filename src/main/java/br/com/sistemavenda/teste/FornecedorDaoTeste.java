package br.com.sistemavenda.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sistemavenda.dao.FornecedorDao;
import br.com.sistemavenda.domain.Fornecedor;

public class FornecedorDaoTeste {

	@Test
	@Ignore
	public void salvar() throws Exception {
		Fornecedor f = new Fornecedor();
		f.setRazaosocial("teste1");
		f.setCnpj("132");
		
		FornecedorDao dao = new FornecedorDao();
		dao.salvar(f);
	}
	
	@Test
	public void listar() throws Exception {
		FornecedorDao dao = new FornecedorDao();
		List<Fornecedor> fornecedores = dao.listar();
		
		for (Fornecedor fornecedor : fornecedores) {
			System.out.println(fornecedor);
		}
	}
}
