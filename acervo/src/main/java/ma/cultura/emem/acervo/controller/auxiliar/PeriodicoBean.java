package ma.cultura.emem.acervo.controller.auxiliar;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.auxiliar.Assunto;
import ma.cultura.emem.acervo.model.auxiliar.Editora;
import ma.cultura.emem.acervo.model.auxiliar.Idioma;
import ma.cultura.emem.acervo.model.auxiliar.Local;
import ma.cultura.emem.acervo.model.auxiliar.Periodico;
import ma.cultura.emem.acervo.model.auxiliar.enums.PeriodicidadeEnum;
import ma.cultura.emem.acervo.service.ItemAcervoService;

@Named
@ViewScoped
public class PeriodicoBean extends AuxiliarBean<Periodico> {

	private static final long serialVersionUID = -2945934767614853255L;

	@Inject
	private ItemAcervoService itemAcervoService;

	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}

	public String recarregarPagina() {
		return "periodico?faces-redirect=true";
	}
	
	public String getStringBotaoGravar(){
		return getPeriodico().getId() == null ? "Gravar" : "Gravar Alterações";
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

	
	public List<Editora> findEditoras(String nome) {
		return itemAcervoService.findEditoras(nome);
	}
	
	public List<Assunto> findAssuntos(String nome) {
		return itemAcervoService.findAssuntos(nome);
	}
	
	public List<Local> findLocais(String nome) {
		return itemAcervoService.findLocais(nome);
	}

	public List<Idioma>  getListaIdiomas() {
		return itemAcervoService.findAllIdiomas();
	}	
}