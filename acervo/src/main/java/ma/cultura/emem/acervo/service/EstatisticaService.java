package ma.cultura.emem.acervo.service;

import java.io.Serializable;

import javax.inject.Inject;

import ma.cultura.emem.acervo.model.CD;
import ma.cultura.emem.acervo.model.Fasciculo;
import ma.cultura.emem.acervo.model.Obra;
import ma.cultura.emem.acervo.model.Partitura;
import ma.cultura.emem.acervo.repository.Consultas;

public class EstatisticaService implements Serializable {
	
	private static final long serialVersionUID = -2300198285854739813L;
	
	@Inject
	private Consultas<Obra> crudObra;
	@Inject
	private Consultas<Partitura> crudPartitura;
	@Inject
	private Consultas<Fasciculo> crudFasciculo;
	@Inject
	private Consultas<CD> crudCD;
	
	public int countAllObras() {
		return crudObra.countAll();
	}
	
	public int countAllPartituras() {
		return crudPartitura.countAll();
	}
	
	public int countAllFasciculos() {
		return crudFasciculo.countAll();
	}
	
	public int countAllCDs() {
		return crudCD.countAll();
	}
}
