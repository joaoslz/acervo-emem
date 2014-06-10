
package ma.cultura.emem.bean;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.bean.datamodel.ObraLazyDataModel;
import ma.cultura.emem.dao.auxiliar.AutorDAO;
import ma.cultura.emem.dao.auxiliar.TipoObraDAO;
import ma.cultura.emem.modelo.Obra;
import ma.cultura.emem.modelo.auxiliar.Autor;
import ma.cultura.emem.modelo.auxiliar.TipoObra;

@Named
@ViewScoped
public class ObraBean extends AbstractItemAcervoBean {

	private static final long serialVersionUID = 3468148839276090985L;
	@Inject
	private AutorDAO autorDAO;
	@Inject
	private TipoObraDAO tipoObraDAO;
	
	@Inject
	private ObraLazyDataModel obraLazyDataModel;
	
	// FIXME Acredito que não será mais preciso este objeto (autorAdd)
	private Autor autorAdd = new Autor();

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
	
	public List<Autor> autocompleteAutorByNome(String nome) {
		List<Autor> autores = autorDAO.findByNome(nome);
		//XXX Talvez seja interessante fazer essa exclusão via query. ???
//		if(!getObra().getAutores().isEmpty())
//			autores.removeAll(getObra().getAutores());
		return autores;
	}
	
	public List<TipoObra> getListaTiposObra(){
		return tipoObraDAO.findAll();
	}
	
	public Obra getObra(){
		return (Obra) getItemAcervo();
	}

	public Autor getAutorAdd() {
		return autorAdd;
	}

	public void setAutorAdd(Autor autorAdd) {
		this.autorAdd = autorAdd;
	}

	public ObraLazyDataModel getObraLazyDataModel() {
		return obraLazyDataModel;
	}

	public void setObraLazyDataModel(ObraLazyDataModel obraLazyDataModel) {
		this.obraLazyDataModel = obraLazyDataModel;
	}
}