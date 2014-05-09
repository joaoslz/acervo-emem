package ma.cultura.emem.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.bean.datamodel.LivroLazyDataModel;
import ma.cultura.emem.modelo.Livro;
import ma.cultura.emem.modelo.Obra;

@Named
@ViewScoped
public class LivroBean extends AbstractObraBean implements Serializable {

	private static final long serialVersionUID = -2182379017227854261L;

	// DAOs
//	@Inject
//	private LivroDAO livroDAO;
	
	@Inject
	private LivroLazyDataModel livroLazyDataModel;
	
	public LivroBean(){
		super();
	}
	
	public Livro getLivro(){
		return (Livro) getObra();
	}

	@Override
	public Obra getNewObra() {
		return new Livro();
	}

	@Override
	public String recarregarPagina() {
		return "livro?faces-redirect=true";
	}

	public LivroLazyDataModel getLivroLazyDataModel() {
		return livroLazyDataModel;
	}
}