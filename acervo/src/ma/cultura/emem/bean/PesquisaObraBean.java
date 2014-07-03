package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.dao.ObraDAO;
import ma.cultura.emem.dao.filtro.ObraFilter;
import ma.cultura.emem.modelo.Obra;
import ma.cultura.emem.modelo.auxiliar.Editora;

@Named
@ViewScoped
public class PesquisaObraBean implements Serializable {

	private static final long serialVersionUID = -8379599414801598582L;
	
	@Inject
	private ObraDAO obraDAO;

	private ObraFilter filtro = new ObraFilter();
	private List<Obra> obrasFiltradas = new ArrayList<Obra>();
	
	
	@Inject
	private ObraBean obraBean;
	
	public ObraFilter getFiltro() {
		return filtro;
	}

	public void pesquisar() {
		obrasFiltradas = obraDAO.filtradas(filtro);	
	}
	
	public List<Obra> getObrasFiltradas() {
		return obrasFiltradas;
	}

	public ObraDAO getObraDAO() {
		return obraDAO;
	}

	public ObraBean getObraBean() {
		return obraBean;
	}
}
