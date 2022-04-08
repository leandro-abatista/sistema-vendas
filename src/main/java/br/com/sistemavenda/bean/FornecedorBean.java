package br.com.sistemavenda.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistemavenda.dao.FornecedorDAO;
import br.com.sistemavenda.domain.Fornecedor;
import br.com.sistemavenda.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedorBean {

	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	private List<Fornecedor> fornecedoresFiltrados = new ArrayList<Fornecedor>();

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


	/**
	 * salva um novo fornecedor
	 * @throws Exception 
	 */
	public void salvar() throws Exception {
		
		try {
			
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.salvar(fornecedor);
			
			//fornecedores = fornecedorDAO.listar();
			
			JSFUtil.adicionarMensagemSucesso("Registro salvo com sucesso!");
			
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

}
