package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ma.cultura.emem.dao.AutorDAO;
import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.dao.EditoraDAO;
import ma.cultura.emem.dao.LivroDAO;
import ma.cultura.emem.dao.LocalDAO;
import ma.cultura.emem.modelo.Autor;
import ma.cultura.emem.modelo.Editora;
import ma.cultura.emem.modelo.Livro;
import ma.cultura.emem.modelo.Local;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

    private Livro livro = new Livro();
    private List<Autor> autoresSelecionados;
    
    private Integer autorId;

    public void gravar() {
	System.out.println("Gravando livro " + this.livro.getTitulo());

//	if (livro.getAutores() == null || livro.getAutores().isEmpty()) {
//	    FacesContext.getCurrentInstance().addMessage("autor",
//		    new FacesMessage("Livro deve ter pelo menos um Autor"));
//	}

	System.out.println("....:::::>>>"+autoresSelecionados);
	new LivroDAO().adiciona(this.livro);
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
	return new LivroDAO().listarLivros();
    }

    public Integer getAutorId() {
	return autorId;
    }

    public void setAutorId(Integer autorId) {
	this.autorId = autorId;
    }

    public Livro getLivro() {
	return livro;
    }

    public List<Editora> likeEditoraByNome(String nome){
	return  new EditoraDAO().likeByNome(nome);
    }

    public List<Local> likeLocalByNome(String nome){
	return  new LocalDAO().likeByNome(nome);
    }

    public List<Autor> likeAutorByNome(String nome){
	return  new AutorDAO().likeByNome(nome);
    }

    public List<Autor> getAutoresSelecionados() {
        return autoresSelecionados;
    }

    public void setAutoresSelecionados(List<Autor> autoresSelecionados) {
        this.autoresSelecionados = autoresSelecionados;
    }
}