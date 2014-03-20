package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import ma.cultura.emem.dao.ObraDAO;
import ma.cultura.emem.modelo.Obra;

@ManagedBean
@ViewScoped
public class PesquisaObraBean implements Serializable {

	private static final long serialVersionUID = -8379599414801598582L;

	private final SelectItem[] listaBuscaRapida = new SelectItem[] { new SelectItem("titulo", "Título"),
			new SelectItem("assunto", "Assunto"), new SelectItem("editora", "Editora") };

	private String textoPesquisa;
	private String titulo;

	public SelectItem[] getListaBuscaRapida() {
		return listaBuscaRapida;
	}

	public String getTextoPesquisa() {
		return textoPesquisa;
	}

	public void setTextoPesquisa(String textoPesquisa) {
		this.textoPesquisa = textoPesquisa;
	}

	public List<Obra> getObrasPorTitulo() {
		System.out.println("------------------------------------");
		System.out.println(this.getTitulo());
		return new ObraDAO(Obra.class).buscaPorTitulo(this.getTitulo());
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
