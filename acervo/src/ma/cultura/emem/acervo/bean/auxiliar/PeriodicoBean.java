package ma.cultura.emem.acervo.bean.auxiliar;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.dao.DAO;
import ma.cultura.emem.acervo.modelo.auxiliar.Editora;
import ma.cultura.emem.acervo.modelo.auxiliar.Idioma;
import ma.cultura.emem.acervo.modelo.auxiliar.Local;
import ma.cultura.emem.acervo.modelo.auxiliar.PeriodicidadeEnum;
import ma.cultura.emem.acervo.modelo.auxiliar.Periodico;

@Named
@ViewScoped
public class PeriodicoBean extends BaseAuxiliarBean<Periodico> {

	private static final long serialVersionUID = -2945934767614853255L;

	@Inject
	private DAO<Editora> editoraDAO;
	@Inject
	private DAO<Local> localDAO;
	@Inject
	private DAO<Idioma> idiomaDAO;
	

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

	public DAO<Editora> getEditoraDAO() {
		return editoraDAO;
	}

	public DAO<Local> getLocalDAO() {
		return localDAO;
	}

	public List<Idioma> getListaIdiomas() {
		return idiomaDAO.findAll();
	}
}