package ma.cultura.emem.bean.auxiliar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.auxiliar.CantorDAO;
import ma.cultura.emem.modelo.auxiliar.Cantor;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

@Named
@ViewScoped
public class CantorBean implements Serializable {

	private static final long serialVersionUID = -7546521322696657212L;
	@Inject
	private Logger logger;
	@Inject
	private CantorDAO cantorDAO;
	private Cantor cantor = new Cantor();
	private List<Cantor> cantores = new ArrayList<Cantor>();

    /**
     * Metodo para editar o cantor direto da tabela.
     * @param event
     */
	public void editCantor(RowEditEvent event) {  
		Cantor a = (Cantor) event.getObject();
		cantorDAO.atualizar(a);
	}  

	public void gravar() {
		cantorDAO.adicionar(cantor);
//		Apos o cadastro o cantor eh adicionado direto no ArrayList 
//		para evitar ter  que atualizar a lista com outra consulta no banco.
		cantores.add(0, cantor);
		cantor = new Cantor();
	}

	public void updateListaCantores() {
		logger.info("listando cantores..");
		cantores = cantorDAO.findAll();
	}

	public List<Cantor> getCantores() {
		if(cantores.isEmpty())
			updateListaCantores();
		return cantores;
	}

	public Cantor getCantor() {
		return cantor;
	}
}
