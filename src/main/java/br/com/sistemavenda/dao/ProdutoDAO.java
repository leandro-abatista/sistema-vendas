package br.com.sistemavenda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistemavenda.domain.Funcionario;
import br.com.sistemavenda.domain.Produto;
import br.com.sistemavenda.util.HibernateUtil;

public class ProdutoDAO {
	
	private Transaction transaction = null; // inicia como nulo
	
	public Produto salvar(Produto produto) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			transaction = session.beginTransaction(); // abrindo uma transa��o
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
	
	@SuppressWarnings("unchecked")
	public List<Produto> listar() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Produto> produtos = null;
		try {

			Query consulta = session.getNamedQuery("Produto.listar");
			produtos = consulta.list();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}

		return produtos;

	}

	public Produto buscarPorId(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Produto produto = null;
		try {

			Query consulta = session.getNamedQuery("Produto.buscarPorId");
			consulta.setLong("id", id);
			produto = (Produto) consulta.uniqueResult();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}

		return produto;
	}

	public void excluir(Produto produto) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction(); // abrindo a transa��o

			session.delete(produto);
			transaction.commit(); // confirma a transa��o
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}
	
	public void excluirPorId(Long id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction(); // abrindo a transa��o
			Produto produto = buscarPorId(id);
			session.delete(produto);
			transaction.commit(); // confirma a transa��o
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}

	public Produto editar(Produto produto) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			transaction = session.beginTransaction(); // abrindo uma transa��o
			session.update(produto);
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
