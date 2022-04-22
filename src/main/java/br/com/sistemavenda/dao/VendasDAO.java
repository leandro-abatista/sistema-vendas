package br.com.sistemavenda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistemavenda.domain.Fornecedor;
import br.com.sistemavenda.domain.Vendas;
import br.com.sistemavenda.util.HibernateUtil;

public class VendasDAO {

	private Transaction transaction = null; // inicia como nulo

	public Long salvar(Vendas vendas) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Long codigo = null;

			transaction = session.beginTransaction(); // abrindo a transação
			codigo = (Long) session.save(vendas);
			transaction.commit();

		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		
		return codigo;

	}

	@SuppressWarnings("unchecked")
	public List<Vendas> listar() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Vendas> vendas = null;
		
		try {

			Query consulta = session.getNamedQuery("Vendas.listar");
			vendas = consulta.list();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}

		return vendas;

	}

	public Vendas buscarPorId(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Vendas venda = null;
		try {

			Query consulta = session.getNamedQuery("Vendas.buscarPorId");
			consulta.setLong("id", id);
			venda = (Vendas) consulta.uniqueResult();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}

		return venda;
	}

	public void excluir(Vendas vendas) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction(); // abrindo a transação
			session.delete(vendas);
			transaction.commit(); // confirma a transação

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
			transaction = session.beginTransaction(); // abrindo a transação
			Vendas venda = buscarPorId(id);
			session.delete(venda);
			transaction.commit(); // confirma a transação

		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

	}

	public void editar(Vendas vendas) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction(); // abrindo a transação
			session.update(vendas);
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
