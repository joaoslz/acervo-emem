package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ma.cultura.emem.dao.AutorDAO;
import ma.cultura.emem.modelo.Autor;

import org.primefaces.event.RowEditEvent;

@ManagedBean
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 3905906075866017417L;

	private AutorDAO autorDAO = new AutorDAO();
	private Autor autor = new Autor();
	private List<Autor> autores = new ArrayList<Autor>();

    /**
     * Método para editar o autor direto da tabela.
     * @param event
     */
	public void editAutor(RowEditEvent event) {  
		Autor a = (Autor) event.getObject();
		autorDAO.atualiza(a);
	}  

	public void gravar() {
		autorDAO.adicionar(autor);
//		Após o cadastro o autor é adicionado direto no ArrayList para evitar atualizar a lista com outro select
		autores.add(0, autor);
		autor = new Autor();
	}

	public void updateListaAutores() {
		autores = autorDAO.listarAutoresPorIdEmOrdemDec();
	}

	public List<Autor> getAutores() {
//		se a lista está vazia é porque o bean acabou de ser criado (nova view), 
//		então faz uma consulta no banco para atualizar a lista.
		if(autores.isEmpty())
			updateListaAutores();
		return autores;
	}

	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
