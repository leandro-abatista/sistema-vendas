package br.com.sistemavenda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistemavenda.domain.Fornecedor;
import br.com.sistemavenda.util.HibernateUtil;

public class FornecedorDAO {

	private Transaction transaction = null; // inicia como nulo

	public void salvar(Fornecedor fornecedor) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Fornecedor> fornecedores = null;
		
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
		Session session = HibernateUtil.getSessionFactory().openSession();
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

	public void excluir(Fornecedor fornecedor) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction(); // abrindo a transa��o
			session.delete(fornecedor);
			transaction.commit(); // confirma a transa��o

		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

	}

	public void excluir(Long id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction(); // abrindo a transa��o
			Fornecedor fornecedor = buscarPorId(id);
			session.delete(fornecedor);
			transaction.commit(); // confirma a transa��o

		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

	}

	public void editar(Fornecedor fornecedor) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction(); // abrindo a transa��o
			Fornecedor fornEditar = buscarPorId(fornecedor.getId());
			fornEditar.setRazaosocial(fornecedor.getRazaosocial());
			fornEditar.setCnpj(fornecedor.getCnpj());
			session.update(fornEditar);
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
