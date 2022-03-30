package br.com.sistemavenda.main;

import br.com.sistemavenda.util.HibernateUtil;

public class GeradorTabelas {

	public static void main(String[] args) {
		
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}
}
