package ma.cultura.emem.acervo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.dao.PesquisaObraDAO;
import ma.cultura.emem.acervo.dao.filtro.ObraFilter;
import ma.cultura.emem.acervo.modelo.Obra;

@Named
@ViewScoped
public class PesquisaObraBean implements Serializable {

	private static final long serialVersionUID = -8379599414801598582L;
	
	@Inject
	private PesquisaObraDAO pesquisaObraDAO;

	private ObraFilter filtro = new ObraFilter();
	private List<Obra> obrasFiltradas = new ArrayList<Obra>();
	
	
	@Inject
	private ObraBean obraBean;
	
	public ObraFilter getFiltro() {
		return filtro;
	}

	public void pesquisar() {
		obrasFiltradas = pesquisaObraDAO.filtradas(filtro);	
	}
	
	public List<Obra> getObrasFiltradas() {
		return obrasFiltradas;
	}

	public PesquisaObraDAO getPesquisaObraDAO() {
		return pesquisaObraDAO;
	}

	public ObraBean getObraBean() {
		return obraBean;
	}
}
