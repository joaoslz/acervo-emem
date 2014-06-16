package ma.cultura.emem.bean.auxiliar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.auxiliar.IdiomaDAO;
import ma.cultura.emem.modelo.auxiliar.Idioma;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

@Named
@ViewScoped
public class IdiomaBean implements Serializable {

	private static final long serialVersionUID = 933860327158419618L;
	@Inject
	private Logger logger;
	@Inject
	private IdiomaDAO idiomaDAO;
	private Idioma idioma = new Idioma();
	private List<Idioma> idiomas = new ArrayList<Idioma>();

	public void editIdioma(RowEditEvent event) {  
		Idioma i = (Idioma) event.getObject();
		idiomaDAO.atualizar(i);
	}  

	public void gravar() {
		idiomaDAO.adicionar(idioma);
		idiomas.add(0, idioma);
		idioma = new Idioma();
	}

	public void updateListaIdiomas() {
		logger.info("listando idiomas..");
		idiomas = idiomaDAO.findAll();
	}

	public List<Idioma> getIdiomas() {
		if(idiomas.isEmpty())
			updateListaIdiomas();
		return idiomas;
	}

	public Idioma getIdioma() {
		return idioma;
	}
}
