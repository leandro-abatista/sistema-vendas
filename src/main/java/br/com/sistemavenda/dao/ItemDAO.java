package br.com.sistemavenda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistemavenda.domain.Item;
import br.com.sistemavenda.domain.Vendas;
import br.com.sistemavenda.util.HibernateUtil;

public class ItemDAO {

	private Transaction transaction = null; // inicia como nulo

	public void salvar(Item item) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction(); // abrindo a transação
			session.save(item);
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
	public List<Item> listar() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Item> itens = null;
		
		try {

			Query consulta = session.getNamedQuery("Item.listar");
			itens = consulta.list();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}

		return itens;

	}

	public Item buscarPorId(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Item item = null;
		try {

			Query consulta = session.getNamedQuery("Item.buscarPorId");
			consulta.setLong("id", id);
			item = (Item) consulta.uniqueResult();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}

		return item;
	}

	public void excluir(Item item) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction(); // abrindo a transação
			session.delete(item);
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
			Item item = buscarPorId(id);
			session.delete(item);
			transaction.commit(); // confirma a transação

		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

	}

	public void editar(Item item) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction(); // abrindo a transação
			session.update(item);
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
