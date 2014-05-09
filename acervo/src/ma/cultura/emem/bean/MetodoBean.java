package ma.cultura.emem.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.MetodoDAO;
import ma.cultura.emem.modelo.Metodo;
import ma.cultura.emem.modelo.Obra;

@Named
@ViewScoped
public class MetodoBean extends AbstractObraBean implements Serializable {

	// DAOs
	@Inject
	private MetodoDAO metodoDAO;
	
	public Metodo getMetodo(){
		return (Metodo) getObra();
	}

	@Override
	public Obra getNewObra() {
		return new Metodo();
	}

	@Override
	public void updateListaObras() {
		lista = metodoDAO.listarTodos();
	}

	@Override
	public String recarregarPagina() {
		return "metodo?faces-redirect=true";
	}
}