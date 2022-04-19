package br.com.sistemavenda.bean;

import java.util.List;

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
	
	private String acao;
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAcao() {
		return acao;
	}
	
	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Fornecedor getFornecedor() {
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

			fornecedores = fornecedorDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Registro salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	//@PostConstruct
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
			
			
			acao = JSFUtil.getParam("fornAcao");
			String valor = JSFUtil.getParam("idFornecedor");
			
			
			//se o id já existir no banco de dados
			if (valor != null) {
				Long id = Long.parseLong(valor);
				fornecedor = fornecedorDAO.buscarPorId(id);
			} else {
				//se for um novo id
				if (fornecedor == null) {
					fornecedor = new Fornecedor();
				}
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
