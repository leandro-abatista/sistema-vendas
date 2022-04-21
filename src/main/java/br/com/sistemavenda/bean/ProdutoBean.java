package br.com.sistemavenda.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistemavenda.dao.FornecedorDAO;
import br.com.sistemavenda.dao.ProdutoDAO;
import br.com.sistemavenda.domain.Fornecedor;
import br.com.sistemavenda.domain.Produto;
import br.com.sistemavenda.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {

	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private FornecedorDAO fornecedorDAO = new FornecedorDAO();
	private Produto produto;
	private List<Produto> produtos = new ArrayList<Produto>();
	private List<Produto> produtoFiltrados = new ArrayList<Produto>();
	
	private String acao;
	private Long id;
	private List<Fornecedor> listaFornecedor;
	
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
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutoFiltrados() {
		return produtoFiltrados;
	}

	public void setProdutoFiltrados(List<Produto> produtoFiltrados) {
		this.produtoFiltrados = produtoFiltrados;
	}
	
	public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}
	
	/**
	 * Método para instânciar um novo produto
	 */
	public void novo() {
		// limpando e criando um novo objeto
		produto = new Produto();
	}

	/**
	 * salva um novo produto
	 * 
	 * @throws Exception
	 */
	public void salvar() throws Exception {

		try {

			produtoDAO.salvar(produto);

			// limpando e criando um novo objeto
			this.novo();

			produtos = produtoDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Registro salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	//@PostConstruct
	public void prepararPesquisa() throws Exception {

		try {

			produtos = produtoDAO.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void editar() {

		try {

			produtoDAO.editar(produto);

			produtos = produtoDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Registro atualizado com sucesso!");
			
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void carregarCadastro() throws Exception {

		try {
			
			
			acao = JSFUtil.getParam("prodAcao");
			String valor = JSFUtil.getParam("idProduto");
			
			
			//se o id já existir no banco de dados
			if (valor != null) {
				Long id = Long.parseLong(valor);
				produto = produtoDAO.buscarPorId(id);
			} else {
				//se for um novo id
				if (produto == null) {
					produto = new Produto();
				}
			}
			listaFornecedor = fornecedorDAO.listar();
			
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluir() throws Exception {

		try {
			produtoDAO.excluir(produto);
			produtos = produtoDAO.listar();
			JSFUtil.adicionarMensagemSucesso("Produto excluído com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir funcionário que tenha uma venda associada!");
			e.printStackTrace();
		}
	}

}
