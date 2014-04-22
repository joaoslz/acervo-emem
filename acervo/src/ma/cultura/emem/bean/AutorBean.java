package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.AutorDAO;
import ma.cultura.emem.modelo.Autor;

import org.primefaces.event.RowEditEvent;

@Named
@ConversationScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 3905906075866017417L;

	@Inject
	private AutorDAO autorDAO;
	private Autor autor = new Autor();
	private List<Autor> autores = new ArrayList<Autor>();

    /**
     * M�todo para editar o autor direto da tabela.
     * @param event
     */
	public void editAutor(RowEditEvent event) {  
		Autor a = (Autor) event.getObject();
		autorDAO.atualizar(a);
	}  

	public void gravar() {
		autorDAO.adicionar(autor);
//		Ap�s o cadastro o autor � adicionado direto no ArrayList 
//		para evitar ter  que atualizar a lista com outra consulta no banco.
		autores.add(0, autor);
		autor = new Autor();
	}

	public void updateListaAutores() {
		autores = autorDAO.listarTodos();
	}

	public List<Autor> getAutores() {
//		se a lista est� vazia � porque o bean acabou de ser criado (nova view), 
//		ent�o faz uma consulta no banco para atualizar a lista.
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
