package br.com.sistemavenda.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistemavenda.domain.Fornecedor;
import br.com.sistemavenda.util.HibernateUtil;

public class FornecedorDao {
	
	private Session session = HibernateUtil.getSessionFactory().openSession();
	private Transaction transaction = null; // inicia como nulo

	public void salvar(Fornecedor fornecedor) throws Exception {

		try {

			transaction = session.beginTransaction(); // abrindo a transa��o
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
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> listar() throws Exception {
		
		List<Fornecedor> fornecedores = new ArrayList();
		try {

			Query consulta = session.getNamedQuery("Fornecedor.listar");
			fornecedores = consulta.list();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		
		return fornecedores;

	}
	
	public Fornecedor buscarPorId(Long id) {
		
		Fornecedor f = null;
		try {

			Query consulta = session.getNamedQuery("Fornecedor.buscarPorId");
			consulta.setLong("id", id);
			f = (Fornecedor) consulta.uniqueResult();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		
		return f;
	}
	
	
}
