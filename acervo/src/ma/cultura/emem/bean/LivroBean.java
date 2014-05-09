package ma.cultura.emem.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.LivroDAO;
import ma.cultura.emem.modelo.Livro;
import ma.cultura.emem.modelo.Obra;

@Named
@ViewScoped
public class LivroBean extends AbstractObraBean implements Serializable {

	// DAOs
	@Inject
	private LivroDAO livroDAO;
	
	public Livro getLivro(){
		return (Livro) getObra();
	}

	@Override
	public Obra getNewObra() {
		return new Livro();
	}

	@Override
	public void updateListaObras() {
		lista = livroDAO.listarTodos();
	}

	@Override
	public String recarregarPagina() {
		return "livro?faces-redirect=true";
	}
}