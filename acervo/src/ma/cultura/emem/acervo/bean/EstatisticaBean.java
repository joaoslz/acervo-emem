package ma.cultura.emem.acervo.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.dao.DAO;
import ma.cultura.emem.acervo.modelo.CD;
import ma.cultura.emem.acervo.modelo.Fasciculo;
import ma.cultura.emem.acervo.modelo.Obra;
import ma.cultura.emem.acervo.modelo.Partitura;

@Named
@ViewScoped
public class EstatisticaBean implements Serializable {

	private static final long serialVersionUID = -1L;
	
	@Inject
	private DAO<Obra> obraDAO;
	@Inject
	private DAO<Partitura> partituraDAO;
	@Inject
	private DAO<Fasciculo> fasciculoDAO;
	@Inject
	private DAO<CD> cdDAO;
	
	public int getTotalObras(){
		return obraDAO.contarTodos();
	}
	
	public int getTotalPartituras(){
		return partituraDAO.contarTodos();
	}

	public int getTotalFasciculo(){
		return fasciculoDAO.contarTodos();
	}
	
	public int getTotalCDs(){
		return cdDAO.contarTodos();
	}
}