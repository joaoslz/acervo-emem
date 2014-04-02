package ma.cultura.emem.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import ma.cultura.emem.dao.EditoraDAO;
import ma.cultura.emem.modelo.Editora;

@ManagedBean
public class EditoraBean implements Serializable {

	private static final long serialVersionUID = -4804077138249718146L;
	private Editora editora = new Editora();

	public void gravar() {
		new EditoraDAO().adicionar(this.editora);
		this.editora = new Editora();
	}
	
	public Editora getEditora() {
		return editora;
	}
	
	public void setEditora(Editora editora) {
	    this.editora = editora;
	}
}
