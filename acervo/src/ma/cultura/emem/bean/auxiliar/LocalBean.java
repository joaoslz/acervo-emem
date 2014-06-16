package ma.cultura.emem.bean.auxiliar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.auxiliar.LocalDAO;
import ma.cultura.emem.modelo.auxiliar.Local;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

@Named
@ViewScoped
public class LocalBean implements Serializable {

	private static final long serialVersionUID = -4374176386849368415L;
	@Inject
	private Logger logger;
	@Inject
	private LocalDAO localDAO;
	private Local local= new Local();
	private List<Local> locais = new ArrayList<Local>();

    /**
     * Metodo para editar o autor direto da tabela.
     * @param event
     */
	public void editLocal(RowEditEvent event) {  
		Local l = (Local) event.getObject();
		localDAO.atualizar(l);
	}  

	public void gravar() {
		localDAO.adicionar(local);
//		Apos o cadastro o autor eh adicionado direto no ArrayList 
//		para evitar ter  que atualizar a lista com outra consulta no banco.
		locais.add(0, local);
		local = new Local();
	}

	public void updateListaLocais() {
		logger.info("listando locais..");
		locais = localDAO.findAll();
	}

	public List<Local> getLocais() {
		if(locais.isEmpty())
			updateListaLocais();
		return locais;
	}

	public Local getLocal() {
		return local;
	}
}
