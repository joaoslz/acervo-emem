package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ma.cultura.emem.dao.EditoraDAO;
import ma.cultura.emem.modelo.Editora;

import org.primefaces.event.RowEditEvent;

@ManagedBean
@ViewScoped
public class EditoraBean implements Serializable {

    private static final long serialVersionUID = -4804077138249718146L;
    private Editora editora = new Editora();
    private EditoraDAO editoraDAO = new EditoraDAO();
    private List<Editora> editoras = new ArrayList<Editora>();
    

    public void updateListaEditoras(){
	editoras = editoraDAO.listarTodasEditoras();
    }

    /**
     * Método para editar a editora direto da tabela.
     * @param event
     */
    public void editEditora(RowEditEvent event) {  
	Editora e = (Editora) event.getObject();
	editoraDAO.merge(e);
    }  
    
    public void gravar() {
	editoraDAO.adicionar(this.editora);
	editoras.add(0,editora);
	this.editora = new Editora();
    }

    public List<Editora> getEditoras(){
	if(editoras.isEmpty())
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
