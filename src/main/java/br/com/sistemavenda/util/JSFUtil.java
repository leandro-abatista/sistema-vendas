package br.com.sistemavenda.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class JSFUtil {

	/**
	 * Mensagem de sucesso
	 * @param mensagem
	 */
	public static void adicionarMensagemSucesso(String mensagem) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, message);
	}

	/**
	 * Mensagem de erro
	 * @param mensagem
	 */
	public static void adicionarMensagemErro(String mensagem) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, message);
	}
	
	public static String getParam(String nome) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		ExternalContext externalContext = contexto.getExternalContext();
		
		Map<String, String> parametros = externalContext.getRequestParameterMap();
		String valor = parametros.get(nome);
		return valor;
	}
}
