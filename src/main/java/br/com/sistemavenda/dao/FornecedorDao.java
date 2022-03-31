package br.com.sistemavenda.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistemavenda.domain.Fornecedor;
import br.com.sistemavenda.util.HibernateUtil;

public class FornecedorDao {
	
	private Session session = HibernateUtil.getSessionFactory().openSession();
	private Transaction transaction = null; // inicia como nulo

	public void salvar(Fornecedor fornecedor) throws Exception {

		try {

			transaction = session.beginTransaction(); // abrindo a transação
			session.save(fornecedor);
			transaction.commit();

		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

	}
}
