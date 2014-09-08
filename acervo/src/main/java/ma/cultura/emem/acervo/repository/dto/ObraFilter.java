package ma.cultura.emem.acervo.repository.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ma.cultura.emem.acervo.model.auxiliar.Editora;
import ma.cultura.emem.acervo.model.auxiliar.TipoObra;

public class ObraFilter implements Serializable {
	
	private static final long serialVersionUID = -8518090812834708389L;
	
	private String titulo;
	private String isbn;
	private String subtitulo;
	private Short anoInicio;
	private Short anoFim;
	
	private Editora editora;
	private List<Integer> autores = new ArrayList<Integer>();
	private List<Integer> assuntos = new ArrayList<Integer>();
	private List<TipoObra> tiposObra = new ArrayList<TipoObra>();
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getSubtitulo() {
		return subtitulo;
	}
	
	public void setSubtitulo(String nomeCliente) {
		this.subtitulo = nomeCliente;
	}
	
	public List<TipoObra> getTiposObra() {
		return tiposObra;
	}
	
	public void setTiposObra(List<TipoObra> tiposObra) {
		this.tiposObra = tiposObra;
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
	
	public Editora getEditora() {
		return editora;
	}
	
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public List<Integer> getAutores() {
		return autores;
	}
	
	public void setAutores(List<Integer> autores) {
		this.autores = autores;
	}
	
	public List<Integer> getAssuntos() {
		return assuntos;
	}
	
	public void setAssuntos(List<Integer> assuntos) {
		this.assuntos = assuntos;
	}
}