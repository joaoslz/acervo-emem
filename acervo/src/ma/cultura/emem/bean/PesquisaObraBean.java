package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ma.cultura.emem.dao.ObraDAO;
import ma.cultura.emem.dao.filtro.ObraFilter;
import ma.cultura.emem.modelo.Obra;

@Named
@ViewScoped
public class PesquisaObraBean implements Serializable {

	private static final long serialVersionUID = -8379599414801598582L;
	
	
//	@Inject
//	private Logger logger;

	@Inject
	private ObraDAO obraDAO;

	private ObraFilter filtro = new ObraFilter();
	private List<Obra> obrasFiltradas = new ArrayList<Obra>();
	
	
	public ObraFilter getFiltro() {
		return filtro;
	}


	public void pesquisar() {
//		logger.debug("Antes de executrar o método que filtra as obras ...");
		obrasFiltradas = obraDAO.filtradas(filtro);
//		logger.debug("Depois de executrar o método que filtra as obras ...");
	}
	
	public List<Obra> getObrasFiltradas() {
		return obrasFiltradas;
	}

}
