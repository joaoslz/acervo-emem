
package ma.cultura.emem.bean;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.bean.datamodel.CDLazyDataModel;
import ma.cultura.emem.dao.MidiaDAO;
import ma.cultura.emem.dao.auxiliar.CantorDAO;
import ma.cultura.emem.dao.auxiliar.GravadoraDAO;
import ma.cultura.emem.modelo.CD;
import ma.cultura.emem.modelo.auxiliar.Cantor;
import ma.cultura.emem.modelo.auxiliar.Gravadora;
import ma.cultura.emem.modelo.auxiliar.Midia;

@Named("cdBean")
@ViewScoped
public class CDBean extends AbstractItemAcervoBean {

	private static final long serialVersionUID = 6482945063096362016L;
	
	@Inject
	private CDLazyDataModel cdLazyDataModel;
	
	@Inject
	private MidiaDAO midiaDAO;
	
	@Inject
	private GravadoraDAO gravadoraDAO;
	@Inject
	private CantorDAO cantorDAO;

	private Gravadora gravadoraAdd = new Gravadora();
	private Cantor cantorAdd = new Cantor();
	
	public void gravarCantor(){
		cantorDAO.adicionar(cantorAdd);
		getCD().getCantores().add(cantorAdd);
		cantorAdd = new Cantor();
	}
	
	public void gravarGravadora(){
		gravadoraDAO.adicionar(gravadoraAdd);
		getCD().setGravadora(gravadoraAdd);
		gravadoraAdd = new Gravadora();
	}
	
	@Override
	public CD getNewItemAcervo() {
		return new CD();
	}
	
	public CD getCD(){
		return (CD) getItemAcervo();
	}

	@Override
	public String recarregarPagina() {
		return "cd?faces-redirect=true";
	}
	
	public List<Midia> getlistaMidias(){
		return midiaDAO.findAll();
	}

	public GravadoraDAO getGravadoraDAO() {
		return gravadoraDAO;
	}

	public CantorDAO getCantorDAO() {
		return cantorDAO;
	}

	public CDLazyDataModel getCdLazyDataModel() {
		return cdLazyDataModel;
	}

	public Gravadora getGravadoraAdd() {
		return gravadoraAdd;
	}

	public void setGravadoraAdd(Gravadora gravadoraAdd) {
		this.gravadoraAdd = gravadoraAdd;
	}

	public Cantor getCantorAdd() {
		return cantorAdd;
	}

	public void setCantorAdd(Cantor cantorAdd) {
		this.cantorAdd = cantorAdd;
	}
}