package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.auxiliar.AutorDAO;
import ma.cultura.emem.modelo.Artigo;
import ma.cultura.emem.modelo.Fasciculo;
import ma.cultura.emem.modelo.auxiliar.Autor;

import org.apache.log4j.Logger;

@Named
@ViewScoped
public class FasciculoBean implements Serializable {

	private static final long serialVersionUID = -8216262637075293980L;

	@Inject
	private Logger logger;
	
	private Fasciculo fasciculo = new Fasciculo();
	
	@Inject
	private AutorDAO autorDAO;


	private Artigo artigoAdd = new Artigo();
	private Autor autorAdd = new Autor();


	public FasciculoBean() {
	}

	public void adicionarArtigo() {
		fasciculo.addArtigo(artigoAdd);
		artigoAdd = new Artigo();
	}

	public void removerArtigo() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int index = Integer.parseInt(params.get("index").toString());
		logger.debug("REMOVENDO artigo index: " + index);
		if (!this.fasciculo.getArtigos().isEmpty()) {
			Artigo a = this.fasciculo.getArtigos().remove(index);
			a.setFasciculo(null);
			logger.debug("REMOVIDO DA LISTA: " + a.getTitulo());
		}
	}
	
	public void limparFormArtigo(){
		artigoAdd = new Artigo();
	}
	
	public void gravarAutorArtigo() {
		autorDAO.adicionar(autorAdd);
		getArtigoAdd().addAutor(autorAdd);
		autorAdd = new Autor();
	}
	
	public List<Autor> autocompleteAutorByNome(String nome) {
		return autorDAO.findByNome(nome);
	}
	

	public Artigo getArtigoAdd() {
		return artigoAdd;
	}

	public void setArtigoAdd(Artigo artigoAdd) {
		this.artigoAdd = artigoAdd;
	}

	public Fasciculo getFasciculo() {
		return fasciculo;
	}

	public void setFasciculo(Fasciculo fasciculo) {
		this.fasciculo = fasciculo;
	}

}