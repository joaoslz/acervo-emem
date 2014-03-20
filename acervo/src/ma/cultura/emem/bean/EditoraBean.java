package ma.cultura.emem.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.Editora;

@ManagedBean
public class EditoraBean implements Serializable {

	private final Editora editora = new Editora();

	public Editora getEditora() {
		return editora;
	}

	public void gravar() {
		new DAO<Editora>(Editora.class).adiciona(this.editora);
	}
}
