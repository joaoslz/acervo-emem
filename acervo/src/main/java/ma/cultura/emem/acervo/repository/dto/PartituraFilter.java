package ma.cultura.emem.acervo.repository.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ma.cultura.emem.acervo.model.auxiliar.Gravadora;

public class PartituraFilter implements Serializable {
	
	private static final long serialVersionUID = -8518090812834708389L;
	
	private String titulo;
	private String subtitulo;
	private Short anoInicio;
	private Short anoFim;
	
	private Gravadora gravadora;
	private List<Integer> arranjadores = new ArrayList<Integer>();
	private List<Integer> instrumentos = new ArrayList<Integer>();
	private List<Integer> autores = new ArrayList<Integer>();
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getSubtitulo() {
		return subtitulo;
	}
	
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	
	public Short getAnoInicio() {
		return anoInicio;
	}
	
	public void setAnoInicio(Short anoInicio) {
		this.anoInicio = anoInicio;
	}
	
	public Short getAnoFim() {
		return anoFim;
	}
	
	public void setAnoFim(Short anoFim) {
		this.anoFim = anoFim;
	}
	
	public Gravadora getGravadora() {
		return gravadora;
	}
	
	public void setGravadora(Gravadora gravadora) {
		this.gravadora = gravadora;
	}
	
	public List<Integer> getArranjadores() {
		return arranjadores;
	}
	
	public void setArranjadores(List<Integer> arranjadores) {
		this.arranjadores = arranjadores;
	}
	
	public List<Integer> getInstrumentos() {
		return instrumentos;
	}
	
	public void setInstrumentos(List<Integer> instrumentos) {
		this.instrumentos = instrumentos;
	}
	
	public List<Integer> getAutores() {
		return autores;
	}
	
	public void setAutores(List<Integer> autores) {
		this.autores = autores;
	}
}