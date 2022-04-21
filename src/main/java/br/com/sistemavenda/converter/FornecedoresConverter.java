package br.com.sistemavenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sistemavenda.dao.FornecedorDAO;
import br.com.sistemavenda.domain.Fornecedor;

@FacesConverter(value = "fornecedoresConverter")
public class FornecedoresConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente, String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			FornecedorDAO dao = new FornecedorDAO();
			Fornecedor fornecedor = dao.buscarPorId(codigo);
			
			return fornecedor;
		} catch (RuntimeException ex) {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente, Object objeto) {
		try {
			Fornecedor fornecedor = (Fornecedor) objeto;
			Long codigo = fornecedor.getId();
			
			return codigo.toString();
		} catch (RuntimeException ex) {
			return null;
		}

	}

}
