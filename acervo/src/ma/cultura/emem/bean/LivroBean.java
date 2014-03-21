package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.Autor;
import ma.cultura.emem.modelo.Livro;
import ma.cultura.emem.modelo.Obra;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -861862195415276394L;
    private Livro livro = new Livro();
    private Integer autorId;

    public Obra getLivro() {
	return livro;
    }

    public void gravar() {
	System.out.println("Gravando livro " + this.livro.getTitulo());

	if (this.livro.getAutores().isEmpty()) {
	    FacesContext.getCurrentInstance().addMessage("autor",
		    new FacesMessage("Livro deve ter pelo menos um Autor"));
	}

	new DAO<Livro>(Livro.class).adiciona(this.livro);
	this.livro = new Livro();

    }

    public List<Autor> getAutores() {
	return new DAO<Autor>(Autor.class).listaTodos();
    }

    public void gravarAutor() {

	Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
	this.livro.adicionarAutor(autor);
    }

    public List<Autor> getAutoresDoLivro() {
	return this.livro.getAutores();
    }

    public List<Livro> getLivros() {
	return new DAO<Livro>(Livro.class).listaTodos();
    }

    public Integer getAutorId() {
	return autorId;
    }

    public void setAutorId(Integer autorId) {
	this.autorId = autorId;
    }

}
