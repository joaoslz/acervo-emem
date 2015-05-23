package ma.cultura.emem.acervo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.Fasciculo;
import ma.cultura.emem.acervo.repository.Pesquisas;
import ma.cultura.emem.acervo.repository.dto.FasciculoFilter;

@Named
@ViewScoped
public class PesquisaFasciculoBean implements Serializable {
	
	private static final long serialVersionUID = -8379599414801598582L;
	
	@Inject
	private Pesquisas pesquisas;
	
	private FasciculoFilter filtro = new FasciculoFilter();
	private List<Fasciculo> fasciculosFiltrados = new ArrayList<Fasciculo>();
	
	public FasciculoFilter getFiltro() {
		return filtro;
	}
	
	public void setFiltro(FasciculoFilter filtro) {
		this.filtro = filtro;
	}
	
	public List<Fasciculo> getFasciculosFiltrados() {
		return fasciculosFiltrados;
	}
	
	public void pesquisar() {
		fasciculosFiltrados = pesquisas.filtrarFasciculos(filtro);
	}
}
