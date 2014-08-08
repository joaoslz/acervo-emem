
package ma.cultura.emem.acervo.bean;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.dao.DAO;
import ma.cultura.emem.acervo.jpa.Transactional;
import ma.cultura.emem.acervo.modelo.Obra;
import ma.cultura.emem.acervo.modelo.auxiliar.Autor;
import ma.cultura.emem.acervo.modelo.auxiliar.TipoObra;

@Named
@ViewScoped
public class ObraBean extends BaseItemAcervoBean<Obra> {

	private static final long serialVersionUID = 3468148839276090985L;
	@Inject
	private DAO<Autor> autorDAO;
	@Inject
	private DAO<TipoObra> tipoObraDAO;
	
	private Autor autorAdd = new Autor();

	@Transactional
	public void gravarAutor() {
		autorDAO.adicionar(autorAdd);
		logger.debug("AUTOR ADD: " + autorAdd);
		getObra().addAutor(autorAdd);
		logger.debug("AUTORES: " + getObra().getAutores());
		autorAdd = new Autor();
	}

	@Override
	public Obra getNewItemAcervo() {
		return new Obra();
	}

	@Override
	public String recarregarPagina() {
		return "obra?faces-redirect=true";
	}
		
	public List<TipoObra> getListaTiposObra(){
		return tipoObraDAO.findAll();
	}
	
	public Obra getObra(){
		return getItemAcervo();
	}

	public Autor getAutorAdd() {
		return autorAdd;
	}

	public void setAutorAdd(Autor autorAdd) {
		this.autorAdd = autorAdd;
	}

	public DAO<Autor> getAutorDAO() {
		return autorDAO;
	}
}