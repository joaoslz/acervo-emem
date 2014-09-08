package ma.cultura.emem.acervo.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.service.EstatisticaService;

@Named
@ViewScoped
public class EstatisticaBean implements Serializable {
	
	private static final long serialVersionUID = -1L;
	
	@Inject
	private EstatisticaService service;
	
	public int getTotalObras() {
		return service.countAllObras();
	}
	
	public int getTotalPartituras() {
		return service.countAllPartituras();
	}
	
	public int getTotalFasciculo() {
		return service.countAllFasciculos();
	}
	
	public int getTotalCDs() {
		return service.countAllCDs();
	}
}