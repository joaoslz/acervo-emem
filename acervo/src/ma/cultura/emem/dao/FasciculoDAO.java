package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Fasciculo;
import ma.cultura.emem.modelo.Periodico;

public class FasciculoDAO extends DAO<Fasciculo> {

	private static final long serialVersionUID = -3281581588744259820L;

	public FasciculoDAO() {
		super(Fasciculo.class);
	}
	
	public List<Fasciculo> findByPeriodicoPaginated(Periodico p, int firstResult, int maxResultsByPage){
		TypedQuery<Fasciculo> query = em.createNamedQuery("Fasciculo.findByPeriodico", Fasciculo.class);
		query.setParameter("idPeriodico", p.getId());
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResultsByPage);
		List<Fasciculo> lista = query.getResultList();
		logger.debug("FASCICULOS SIZE: " + lista.size());
		return lista;
	}
}
