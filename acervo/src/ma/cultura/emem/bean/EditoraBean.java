package ma.cultura.emem.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.Editora;

@ManagedBean
public class EditoraBean implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -4804077138249718146L;
    private final Editora editora = new Editora();

    public Editora getEditora() {
	return editora;
    }

    public void gravar() {
	new DAO<Editora>(Editora.class).adicionar(this.editora);
    }
}
