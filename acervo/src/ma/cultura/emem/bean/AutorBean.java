package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import ma.cultura.emem.dao.AutorDAO;
import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.Autor;

import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 3905906075866017417L;

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		new DAO<Autor>(Autor.class).adicionar(this.autor);
		this.autor = new Autor();
	}

	// public List<Autor> getListaAutores() {
	// return new DAO<Autor>(Autor.class).listaTodos();
	// }

	public List<Autor> getListaAutoresPorIdEmOrdemDec() {
		return new AutorDAO().listarAutoresPorIdEmOrdemDec();
	}

	public void gravar(ActionEvent actionEvent) {
		RequestContext.getCurrentInstance();
	}
}
