package ma.cultura.emem.bean.auxiliar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.auxiliar.AutorDAO;
import ma.cultura.emem.modelo.auxiliar.Autor;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 3905906075866017417L;

	@Inject
	private Logger logger;
	@Inject
	private AutorDAO autorDAO;
	private Autor autor = new Autor();
	private List<Autor> autores = new ArrayList<Autor>();

    /**
     * Metodo para editar o autor direto da tabela.
     * @param event
     */
	public void editAutor(RowEditEvent event) {  
		Autor a = (Autor) event.getObject();
		autorDAO.atualizar(a);
	}  

	public void gravar() {
		autorDAO.adicionar(autor);
//		Apos o cadastro o autor eh adicionado direto no ArrayList 
//		para evitar ter  que atualizar a lista com outra consulta no banco.
		autores.add(0, autor);
		autor = new Autor();
	}

	public void updateListaAutores() {
		logger.info("listando autores..");
		autores = autorDAO.findAll();
	}

	public List<Autor> getAutores() {
		if(autores.isEmpty())
			updateListaAutores();
		return autores;
	}

	public Autor getAutor() {
		return autor;
	}
}
