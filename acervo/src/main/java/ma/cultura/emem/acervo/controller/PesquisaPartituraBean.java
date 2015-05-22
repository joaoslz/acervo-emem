package ma.cultura.emem.acervo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.Partitura;
import ma.cultura.emem.acervo.repository.Pesquisas;
import ma.cultura.emem.acervo.repository.dto.PartituraFilter;

@Named
@ViewScoped
public class PesquisaPartituraBean implements Serializable {
	
	private static final long serialVersionUID = -8379599414801598582L;
	
	@Inject
	private Pesquisas pesquisas;
	
	private PartituraFilter filtro = new PartituraFilter();
	private List<Partitura> partiturasFiltradas = new ArrayList<Partitura>();
	
	public PartituraFilter getFiltro() {
		return filtro;
	}
	
	public void setFiltro(PartituraFilter filtro) {
		this.filtro = filtro;
	}
	
	public List<Partitura> getPartiturasFiltradas() {
		return partiturasFiltradas;
	}
	
	public void pesquisar() {
		partiturasFiltradas = pesquisas.filtrarPartituras(filtro);
	}
}
