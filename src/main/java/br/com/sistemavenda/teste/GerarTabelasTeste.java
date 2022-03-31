package br.com.sistemavenda.teste;

import org.junit.Test;

import br.com.sistemavenda.util.HibernateUtil;

public class GerarTabelasTeste {

	@Test
	public void gerar() {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}
	
}
