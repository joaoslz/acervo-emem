package ma.cultura.emem.acervo.repository.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ma.cultura.emem.acervo.model.auxiliar.Gravadora;
import ma.cultura.emem.acervo.model.auxiliar.Midia;

public class RevistaFilter implements Serializable {
	
	private static final long serialVersionUID = -8518090812834708389L;
	
	private String titulo;
	private String subtitulo;
	private String comentario;
	private Short anoInicio;
	private Short anoFim;
	private String musica;
	
	private Gravadora gravadora;
	private List<Integer> cantores = new ArrayList<Integer>();
	private List<Integer> assuntos = new ArrayList<Integer>();
	private List<Midia> midias = new ArrayList<Midia>();
	
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

	public List<Integer> getCantores() {
		return cantores;
	}
	public void setCantores(List<Integer> cantores) {
		this.cantores = cantores;
	}
	public List<Integer> getAssuntos() {
		return assuntos;
	}
	public void setAssuntos(List<Integer> assuntos) {
		this.assuntos = assuntos;
	}
	public List<Midia> getMidias() {
		return midias;
	}
	public void setMidias(List<Midia> midias) {
		this.midias = midias;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getMusica() {
		return musica;
	}
	public void setMusica(String musica) {
		this.musica = musica;
	}
}