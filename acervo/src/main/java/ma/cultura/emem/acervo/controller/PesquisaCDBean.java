package ma.cultura.emem.acervo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.CD;
import ma.cultura.emem.acervo.model.auxiliar.Assunto;
import ma.cultura.emem.acervo.model.auxiliar.Cantor;
import ma.cultura.emem.acervo.model.auxiliar.Gravadora;
import ma.cultura.emem.acervo.repository.Pesquisas;
import ma.cultura.emem.acervo.repository.dto.CDFilter;
import ma.cultura.emem.acervo.service.ItemAcervoService;

@Named
@ViewScoped
public class PesquisaCDBean implements Serializable {
	
	private static final long serialVersionUID = -8379599414801598582L;
	
	@Inject
	private Pesquisas pesquisas;
	@Inject
	private ItemAcervoService itemAcervoService;
	
	private CDFilter filtro = new CDFilter();
	private List<CD> cdsFiltradas = new ArrayList<CD>();
	
	public CDFilter getFiltro() {
		return filtro;
	}
	
	public void setFiltro(CDFilter filtro) {
		this.filtro = filtro;
	}
	
	public List<CD> getCDsFiltrados() {
		return cdsFiltradas;
	}
	
	public void pesquisar() {
		cdsFiltradas = pesquisas.filtrarCDs(filtro);
	}
	
	//FIXME criar um ManagedBean para colocar todos estes métodos de autocomplete 
	//para reutilização em todos os formularios. 
	public List<Gravadora> findGravadoras(String nome) {
		return itemAcervoService.findGravadoras(nome);
	}
	
	public List<Assunto> findAssuntos(String nome) {
		return itemAcervoService.findAssuntos(nome);
	}
	
	public List<Cantor> findCantores(String nome) {
		return itemAcervoService.findCantores(nome);
	}
}
