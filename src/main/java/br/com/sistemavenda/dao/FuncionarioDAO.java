package br.com.sistemavenda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistemavenda.domain.Funcionario;
import br.com.sistemavenda.util.HibernateUtil;

public class FuncionarioDAO {

	private Transaction transaction = null; // inicia como nulo

	public void salvar(Funcionario funcionario) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction(); // abrindo a transação
			session.save(funcionario);
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
	public List<Funcionario> listar() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Funcionario> funcionarios = null;
		try {

			Query consulta = session.getNamedQuery("Funcionario.listar");
			funcionarios = consulta.list();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}

		return funcionarios;

	}

	public Funcionario buscarPorId(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;
		try {

			Query consulta = session.getNamedQuery("Funcionario.buscarPorId");
			consulta.setLong("id", id);
			funcionario = (Funcionario) consulta.uniqueResult();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}

		return funcionario;
	}

	public void excluir(Funcionario funcionario) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction(); // abrindo a transação

			session.delete(funcionario);
			transaction.commit(); // confirma a transação
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
			transaction = session.beginTransaction(); // abrindo a transação
			Funcionario funcionario = buscarPorId(id);
			session.delete(funcionario);
			transaction.commit(); // confirma a transação

		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

	}

	public void editar(Funcionario funcionario) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction(); // abrindo a transação
			Funcionario funcEditar = buscarPorId(funcionario.getId());
			funcEditar.setNome(funcionario.getNome());
			funcEditar.setCpf(funcionario.getCpf());
			funcEditar.setSenha(funcionario.getSenha());
			funcEditar.setFuncao(funcionario.getFuncao());

			session.update(funcEditar);
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
