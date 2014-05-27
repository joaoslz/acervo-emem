package ma.cultura.emem.bean;

import java.util.List;

import javax.inject.Inject;

import ma.cultura.emem.dao.AutorDAO;
import ma.cultura.emem.modelo.Obra;
import ma.cultura.emem.modelo.auxiliar.Autor;

public abstract class AbstractObraBean extends AbstractItemAcervoBean {

	@Inject
	protected AutorDAO autorDAO;
	
	// FIXME Acredito que não será mais preciso este objeto (autorAdd)
	protected Autor autorAdd = new Autor();

	public void gravarAutor() {
		autorDAO.adicionar(autorAdd);
		getObra().addAutor(autorAdd);
		autorAdd = new Autor();
	}

	public List<Autor> autocompleteAutorByNome(String nome) {
		return autorDAO.findByNome(nome);
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
}