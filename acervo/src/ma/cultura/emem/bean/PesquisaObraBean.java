package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.ObraDAO;
import ma.cultura.emem.modelo.Obra;

import org.apache.log4j.Logger;

@Named
@RequestScoped
public class PesquisaObraBean implements Serializable {

	@Inject
	private Logger logger;
	private static final long serialVersionUID = -8379599414801598582L;

	@Inject
	private ObraDAO obraDAO;
	
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
		logger.debug("titulo: " + titulo);
		List lista =  obraDAO.pesquisarPorTitulo(titulo);
		
		StringBuilder builder = new StringBuilder();
		for(Object o: lista){
			builder.append("\nCLASS: " + o.getClass().getSimpleName());
		}
		logger.debug(builder);
		return lista;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
