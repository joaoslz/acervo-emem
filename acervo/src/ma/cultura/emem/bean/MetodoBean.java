package ma.cultura.emem.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.bean.datamodel.MetodoLazyDataModel;
import ma.cultura.emem.modelo.Metodo;
import ma.cultura.emem.modelo.Obra;

@Named
@ViewScoped
public class MetodoBean extends AbstractObraBean implements Serializable {

	private static final long serialVersionUID = -55181606637412623L;
	// DAOs
//	@Inject
//	private MetodoDAO metodoDAO;
	@Inject
	private MetodoLazyDataModel metodoLazyDataModel;

	public Metodo getLivro(){
		return (Metodo) getObra();
	}
	
	public Metodo getMetodo(){
		return (Metodo) getObra();
	}

	@Override
	public Obra getNewObra() {
		return new Metodo();
	}

	@Override
	public String recarregarPagina() {
		return "metodo?faces-redirect=true";
	}

	public MetodoLazyDataModel getMetodoLazyDataModel() {
		return metodoLazyDataModel;
	}
}