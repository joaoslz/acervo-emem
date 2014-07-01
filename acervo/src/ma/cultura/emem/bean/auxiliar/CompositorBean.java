package ma.cultura.emem.bean.auxiliar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.auxiliar.CompositorDAO;
import ma.cultura.emem.modelo.auxiliar.Compositor;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

@Named
@ViewScoped
public class CompositorBean implements Serializable {

	private static final long serialVersionUID = -7546521322696657212L;
	@Inject
	private Logger logger;
	@Inject
	private CompositorDAO compositorDAO;
	private Compositor compositor = new Compositor();
	private List<Compositor> compositores = new ArrayList<Compositor>();

    /**
     * Metodo para editar o compositor direto da tabela.
     * @param event
     */
	public void editCompositor(RowEditEvent event) {  
		Compositor a = (Compositor) event.getObject();
		compositorDAO.atualizar(a);
	}  

	public void gravar() {
		compositorDAO.adicionar(compositor);
//		Apos o cadastro o compositor eh adicionado direto no ArrayList 
//		para evitar ter  que atualizar a lista com outra consulta no banco.
		compositores.add(0, compositor);
		compositor = new Compositor();
	}

	public void updateListaCompositores() {
		logger.info("listando compositores..");
		compositores = compositorDAO.findAll();
	}

	public List<Compositor> getCompositores() {
		if(compositores.isEmpty())
			updateListaCompositores();
		return compositores;
	}

	public Compositor getCompositor() {
		return compositor;
	}
}
