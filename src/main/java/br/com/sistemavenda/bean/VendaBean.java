package br.com.sistemavenda.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistemavenda.dao.ProdutoDAO;
import br.com.sistemavenda.domain.Item;
import br.com.sistemavenda.domain.Produto;
import br.com.sistemavenda.util.JSFUtil;

@ManagedBean(name = "MBVenda")
@ViewScoped
public class VendaBean {

	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private Produto produto;
	private List<Item> itens;
	private List<Produto> produtos;
	private List<Produto> produtoFiltrados;

	private List<Produto> itensFiltrados;

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

	public List<Item> getItens() {
		if (itens == null) {
			itens = new ArrayList<Item>();//inicia um novo item
		}
		return itens;
	}

	public List<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(List<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
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

	// @PostConstruct
	public void carregarProdutos() throws Exception {

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

			// acao = JSFUtil.getParam("prodAcao");
			// String valor = JSFUtil.getParam("idProduto");

			/*
			 * se o id já existir no banco de dados if (valor != null) { Long id =
			 * Long.parseLong(valor); produto = produtoDAO.buscarPorId(id); } else { //se
			 * for um novo id if (produto == null) { produto = new Produto(); } }
			 * listaFornecedor = fornecedorDAO.listar();
			 */
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

	public void adicionar(Produto produto) {
		
		int posicaoEncontrada = -1;
		
		for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {
			
			//pega a posição atual do item
			Item itemTemp = itens.get(pos);
			
			if (itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}
		}
		
		Item item = new Item();
		item.setProduto(produto);
		
		if(posicaoEncontrada < 0) {
			item.setQuantidade(1);
			item.setValorParcial(produto.getPreco());
			itens.add(item);
		}else {
		
			Item itemTemp = itens.get(posicaoEncontrada);
			item.setQuantidade(itemTemp.getQuantidade() + 1);
			item.setValorParcial(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			itens.set(posicaoEncontrada, item);
		}
	}
	
	public void remover(Item item) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {

			// pega a posição atual do item
			Item itemTemp = itens.get(pos);

			if (itemTemp.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = pos;
			}
		}
		
		if (posicaoEncontrada > -1) {
			itens.remove(posicaoEncontrada);
		}
	}

}
