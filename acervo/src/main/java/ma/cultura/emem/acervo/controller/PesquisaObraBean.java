package ma.cultura.emem.acervo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.Obra;
import ma.cultura.emem.acervo.repository.dto.ObraFilter;
import ma.cultura.emem.acervo.service.PesquisaService;

@Named
@ViewScoped
public class PesquisaObraBean implements Serializable {

	private static final long serialVersionUID = -8379599414801598582L;
	
	@Inject
	private PesquisaService service;

	private ObraFilter filtro = new ObraFilter();
	private List<Obra> obrasFiltradas = new ArrayList<Obra>();
		
	public ObraFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ObraFilter filtro) {
		this.filtro = filtro;
	}

	public List<Obra> getObrasFiltradas() {
		return obrasFiltradas;
	}

	public void pesquisar() {
		obrasFiltradas = service.filtrarObras(filtro);	
	}
	
	
}
