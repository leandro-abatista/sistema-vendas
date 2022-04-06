package br.com.sistemavenda.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistemavenda.domain.Produto;
import br.com.sistemavenda.util.HibernateUtil;

public class ProdutoDAO {
	
	private Transaction transaction = null; // inicia como nulo
	
	public Produto salvar(Produto produto) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			transaction = session.beginTransaction(); // abrindo uma transação
			session.save(produto);
			transaction.commit();
			
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}  finally {
			session.close();
		}
		
		return produto;
	}

}
