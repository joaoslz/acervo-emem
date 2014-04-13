package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ma.cultura.emem.dao.AssuntoDAO;
import ma.cultura.emem.modelo.Assunto;

import org.primefaces.event.RowEditEvent;

@ManagedBean
@ViewScoped
public class AssuntoBean implements Serializable {

	private static final long serialVersionUID = 3905906075866017417L;

	private Assunto assunto = new Assunto();
	private AssuntoDAO assuntoDAO = new AssuntoDAO();
	List<Assunto> assuntos = new ArrayList<Assunto>();

	public Assunto getAssunto() {
		return assunto;
	}

	public void gravar() {
		assuntoDAO.adicionar(this.assunto);
//		Após o cadastro o autor é adicionado direto no ArrayList 
//		para evitar ter  que atualizar a lista com outra consulta no banco.
		assuntos.add(0, this.assunto);
		this.assunto = new Assunto();
	}

	public void updateListaAssuntos() {
		assuntos = assuntoDAO.listarAssuntosPorIdEmOrdemDec();
	}

	public List<Assunto> getAssuntos() {
		// se a lista está vazia é porque o bean acabou de ser criado (nova view),
		// então faz uma consulta no banco para atualizar a lista.
		if (assuntos.isEmpty())
			updateListaAssuntos();
		return assuntos;
	}

	/**
	 * Método para editar o assunto direto da tabela.
	 * 
	 * @param event
	 */
	public void editAssunto(RowEditEvent event) {
		Assunto a = (Assunto) event.getObject();
		assuntoDAO.atualiza(a);
	}

}
