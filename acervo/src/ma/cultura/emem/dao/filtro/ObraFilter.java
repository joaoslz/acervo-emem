package ma.cultura.emem.dao.filtro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ma.cultura.emem.modelo.auxiliar.Editora;
import ma.cultura.emem.modelo.auxiliar.TipoObra;

public class ObraFilter implements Serializable {

	private static final long serialVersionUID = -8518090812834708389L;

	private String titulo;
	private String subtitulo;
	private Short anoInicio;
	private Short anoFim;

	private Editora editora;
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

}