package br.com.sistemavenda.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistemavenda.dao.FornecedorDAO;
import br.com.sistemavenda.domain.Fornecedor;
import br.com.sistemavenda.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedorBean {

	private FornecedorDAO fornecedorDAO = new FornecedorDAO();
	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores;
	private List<Fornecedor> fornecedoresFiltrados;

	public Fornecedor getFornecedor() {
		if (fornecedor == null) {
			fornecedor = new Fornecedor();
		}
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public List<Fornecedor> getFornecedoresFiltrados() {
		return fornecedoresFiltrados;
	}

	public void novo() {
		// limpando e criando um novo objeto
		fornecedor = new Fornecedor();
	}

	/**
	 * salva um novo fornecedor
	 * 
	 * @throws Exception
	 */
	public void salvar() throws Exception {

		try {

			fornecedorDAO.salvar(fornecedor);

			// limpando e criando um novo objeto
			this.novo();

			// fornecedores = fornecedorDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Registro salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	// @PostConstruct
	public void prepararPesquisa() throws Exception {

		try {

			fornecedores = fornecedorDAO.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void editar() {

		try {

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.editar(fornecedor);

			fornecedores = fornecedorDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Registro atualizado com sucesso!");

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void carregarCadastro() {

		try {

			String valor = JSFUtil.getParam("idFornecedor");
			
			if (valor != null) {
				Long id = Long.parseLong(valor);
				fornecedor = fornecedorDAO.buscarPorId(id);
			}

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void excluir() throws Exception {

		try {

			fornecedorDAO.excluir(fornecedor);
			fornecedores = fornecedorDAO.listar();
			JSFUtil.adicionarMensagemSucesso("Fornecedor excluído com sucesso!");
			
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir fornecedor que tenha um produto associado!");
			e.printStackTrace();
		}
	}

}
