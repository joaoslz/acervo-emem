package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.EditoraDAO;
import ma.cultura.emem.modelo.Editora;

import org.primefaces.event.RowEditEvent;

@Named
@ViewScoped
public class EditoraBean implements Serializable {

	private static final long serialVersionUID = -4804077138249718146L;

	@Inject
	private EditoraDAO editoraDAO;

	private Editora editora = new Editora();
	private List<Editora> editoras = new ArrayList<Editora>();

	public void updateListaEditoras() {
		editoras = editoraDAO.listarTodos();
	}

	/**
	 * Método para editar a editora direto da tabela.
	 * @param event
	 */
	public void editEditora(RowEditEvent event) {
		Editora e = (Editora) event.getObject();
		editoraDAO.atualizar(e);
	}

	public void gravar() {
		editoraDAO.adicionar(this.editora);
		editoras.add(0, editora);
		editora = new Editora();
	}

	public List<Editora> getEditoras() {
		if (editoras.isEmpty())
			updateListaEditoras();
		return editoras;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

}
