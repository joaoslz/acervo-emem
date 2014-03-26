package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ma.cultura.emem.dao.AutorDAO;
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
    
    public void gravar() {
	System.out.println("Gravando livro " + livro.getTitulo());
	System.out.println("....:::::>>>autores: " + livro.getAutores());
	new LivroDAO().adicionar(livro);
	livro = new Livro();
    }

    public List<Livro> getListaLivros() {
	return new LivroDAO().listarLivros();
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

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}