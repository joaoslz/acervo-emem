package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import ma.cultura.emem.dao.EditoraDAO;
import ma.cultura.emem.modelo.Editora;

@ManagedBean
public class EditoraBean implements Serializable {

    private static final long serialVersionUID = -4804077138249718146L;
    private Editora editora = new Editora();

    public Editora getEditora() {
	return editora;
    }

    public String gravar() {
	new EditoraDAO().adicionar(editora);
	editora = new Editora();
	return "editora?faces-redirect=true";
    }
    
    public List<Editora> getListaEditoras(){
	return new EditoraDAO().listarTodas();
    }
}
