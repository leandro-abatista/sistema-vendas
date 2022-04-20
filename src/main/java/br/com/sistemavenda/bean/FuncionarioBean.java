package br.com.sistemavenda.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistemavenda.dao.FuncionarioDAO;
import br.com.sistemavenda.domain.Funcionario;
import br.com.sistemavenda.util.JSFUtil;

@ManagedBean(name = "MBFuncionario")
@ViewScoped
public class FuncionarioBean {

	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private Funcionario funcionario;
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private List<Funcionario> funcionariosFiltrados = new ArrayList<Funcionario>();
	
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
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFuncionariosFiltrados() {
		return funcionariosFiltrados;
	}

	public void setFuncionariosFiltrados(List<Funcionario> funcionariosFiltrados) {
		this.funcionariosFiltrados = funcionariosFiltrados;
	}

	public void novo() {
		// limpando e criando um novo objeto
		funcionario = new Funcionario();
	}

	/**
	 * salva um novo funcionario
	 * 
	 * @throws Exception
	 */
	public void salvar() throws Exception {

		try {

			funcionarioDAO.salvar(funcionario);

			// limpando e criando um novo objeto
			this.novo();

			funcionarios = funcionarioDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Registro salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	//@PostConstruct
	public void prepararPesquisa() throws Exception {

		try {

			funcionarios = funcionarioDAO.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void editar() {

		try {

			funcionarioDAO.editar(funcionario);

			funcionarios = funcionarioDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Registro atualizado com sucesso!");
			
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void carregarCadastro() {

		try {
			
			
			acao = JSFUtil.getParam("funAcao");
			String valor = JSFUtil.getParam("idFuncionario");
			
			
			//se o id já existir no banco de dados
			if (valor != null) {
				Long id = Long.parseLong(valor);
				funcionario = funcionarioDAO.buscarPorId(id);
			} else {
				//se for um novo id
				if (funcionario == null) {
					funcionario = new Funcionario();
				}
			}

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluir() throws Exception {

		try {
			funcionarioDAO.excluir(funcionario);
			funcionarios = funcionarioDAO.listar();
			JSFUtil.adicionarMensagemSucesso("Funcionario excluído com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir funcionário que tenha uma venda associada!");
			e.printStackTrace();
		}
	}

}
