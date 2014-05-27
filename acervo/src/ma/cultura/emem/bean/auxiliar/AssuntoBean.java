package ma.cultura.emem.bean.auxiliar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.AssuntoDAO;
import ma.cultura.emem.modelo.auxiliar.Assunto;

import org.primefaces.event.RowEditEvent;

@Named
@ViewScoped
public class AssuntoBean implements Serializable {

	private static final long serialVersionUID = 3905906075866017417L;

	@Inject
	private AssuntoDAO assuntoDAO;
	private Assunto assunto = new Assunto();
	private List<Assunto> assuntos = new ArrayList<Assunto>();
	
	public void editAssunto(RowEditEvent event) {
		Assunto a = (Assunto) event.getObject();
		assuntoDAO.atualizar(a);
	}

	public void updateListaAssuntos(){
		assuntos = assuntoDAO.findAll();
	}

	public void gravar() {
		assuntoDAO.adicionar(this.assunto);
//		Apos o cadastro o autor eh adicionado direto no ArrayList 
//		para evitar ter  que atualizar a lista com outra consulta no banco.
		assuntos.add(0, this.assunto);
		this.assunto = new Assunto();
	}

	public List<Assunto> getAssuntos() {
		// se a lista est� vazia � porque o bean acabou de ser criado (nova view),
		// ent�o faz uma consulta no banco para atualizar a lista.
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
