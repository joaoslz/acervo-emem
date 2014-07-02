package ma.cultura.emem.bean.auxiliar;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.auxiliar.EditoraDAO;
import ma.cultura.emem.dao.auxiliar.IdiomaDAO;
import ma.cultura.emem.dao.auxiliar.LocalDAO;
import ma.cultura.emem.modelo.Periodico;
import ma.cultura.emem.modelo.auxiliar.Idioma;
import ma.cultura.emem.modelo.auxiliar.PeriodicidadeEnum;

@Named
@ViewScoped
public class PeriodicoBean extends BaseAuxiliarBean<Periodico> {

	private static final long serialVersionUID = -2945934767614853255L;

	@Inject
	private EditoraDAO editoraDAO;
	@Inject
	private LocalDAO localDAO;
	@Inject
	private IdiomaDAO idiomaDAO;
	

	public String recarregarPagina() {
		return "periodico?faces-redirect=true";
	}
	
	public String getStringBotaoGravar(){
		if(getPeriodico().getId() == null)
			return "Gravar";
		else
			return "Gravar Alterações";
	}
	
	public PeriodicidadeEnum[] getListaPeriodicidade(){
		return PeriodicidadeEnum.values();
	}
	
	public Periodico getPeriodico(){
		return getEntity();
	}
	
	public void setPeriodico(Periodico p){
		setEntity(p);
	}

	@Override
	protected Periodico newEntityInstance() {
		return new Periodico();
	}

	public EditoraDAO getEditoraDAO() {
		return editoraDAO;
	}

	public LocalDAO getLocalDAO() {
		return localDAO;
	}

	public List<Idioma> getListaIdiomas() {
		return idiomaDAO.findAll();
	}
}