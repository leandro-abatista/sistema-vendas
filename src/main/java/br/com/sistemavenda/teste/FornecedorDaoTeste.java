package br.com.sistemavenda.teste;

import org.junit.Test;

import br.com.sistemavenda.dao.FornecedorDao;
import br.com.sistemavenda.domain.Fornecedor;

public class FornecedorDaoTeste {

	@Test
	public void salvar() throws Exception {
		Fornecedor f = new Fornecedor();
		f.setRazaosocial("teste1");
		f.setCnpj("132");
		
		FornecedorDao dao = new FornecedorDao();
		dao.salvar(f);
	}
}
