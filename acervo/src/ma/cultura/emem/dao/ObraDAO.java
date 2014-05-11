package ma.cultura.emem.dao;


import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Obra;

import org.apache.log4j.Logger;

public class ObraDAO extends DAO<Obra> {
	private static final long serialVersionUID = -2807083787187739746L;
	private static final Logger LOGGER = Logger.getLogger(ObraDAO.class);
	
	public ObraDAO() {
		super(Obra.class);
	}
	
	public List<Obra> pesquisarPorTitulo(String titulo) {
		TypedQuery<Obra> query = em.createNamedQuery("Obra.pesquisarPorTitulo", Obra.class);
		query.setParameter("titulo", "%" + titulo + "%");
		return query.getResultList();
	}
}