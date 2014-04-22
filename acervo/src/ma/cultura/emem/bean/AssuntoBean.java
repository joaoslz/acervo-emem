package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.AssuntoDAO;
import ma.cultura.emem.modelo.Assunto;

import org.primefaces.event.RowEditEvent;

@Named
@ConversationScoped
public class AssuntoBean implements Serializable {

	private static final long serialVersionUID = 3905906075866017417L;

	@Inject
	private AssuntoDAO assuntoDAO;
	private Assunto assunto = new Assunto();
	private List<Assunto> assuntos = new ArrayList<Assunto>();
	

	/**
	 * Método para editar o assunto direto da tabela.
	 * 
	 * @param event
	 */
	public void editAssunto(RowEditEvent event) {
		Assunto a = (Assunto) event.getObject();
		assuntoDAO.atualizar(a);
	}

	public void updateListaAssuntos(){
		assuntos = assuntoDAO.listarTodos();
	}

	public void gravar() {
		assuntoDAO.adicionar(this.assunto);
//		Após o cadastro o autor é adicionado direto no ArrayList 
//		para evitar ter  que atualizar a lista com outra consulta no banco.
		assuntos.add(0, this.assunto);
		this.assunto = new Assunto();
	}

	public List<Assunto> getAssuntos() {
		// se a lista está vazia é porque o bean acabou de ser criado (nova view),
		// então faz uma consulta no banco para atualizar a lista.
		if (assuntos.isEmpty())
			updateListaAssuntos();
		return assuntos;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}
}
