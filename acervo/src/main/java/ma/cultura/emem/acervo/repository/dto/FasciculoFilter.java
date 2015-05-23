package ma.cultura.emem.acervo.repository.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ma.cultura.emem.acervo.model.auxiliar.Editora;
import ma.cultura.emem.acervo.model.auxiliar.Local;
import ma.cultura.emem.acervo.model.auxiliar.enums.MesEnum;

public class FasciculoFilter implements Serializable {
	
	private static final long serialVersionUID = -8518090812834708389L;
	
	private String titulo;
	private String subtitulo;
	private Short anoInicio;
	private Short anoFim;
	private Short volume;
	private MesEnum mes;
	private List<Integer> assuntos = new ArrayList<Integer>();
	
	//Periódico
	private String issn;
	private Boolean ehAssinado;
	private Editora editora;
	private Local local;
	//Artigo
	private String artigo;
	

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

	public Short getVolume() {
		return volume;
	}

	public void setVolume(Short volume) {
		this.volume = volume;
	}

	public MesEnum getMes() {
		return mes;
	}

	public void setMes(MesEnum mes) {
		this.mes = mes;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public Boolean getEhAssinado() {
		return ehAssinado;
	}

	public void setEhAssinado(Boolean ehAssinado) {
		this.ehAssinado = ehAssinado;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getArtigo() {
		return artigo;
	}

	public void setArtigo(String artigo) {
		this.artigo = artigo;
	}

	public List<Integer> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Integer> assuntos) {
		this.assuntos = assuntos;
	}
}