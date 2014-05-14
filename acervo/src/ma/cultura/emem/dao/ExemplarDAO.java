package ma.cultura.emem.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Exemplar;

import org.apache.log4j.Logger;

public class ExemplarDAO extends DAO<Exemplar> {

	private static final long serialVersionUID = -558780804432621543L;
	@Inject
	private Logger logger;

	public ExemplarDAO() {
		super(Exemplar.class);
	}

	public void adicionarExemplares(List<Exemplar> exemplares) {
		em.getTransaction().begin();
		for (Exemplar ex : exemplares) {
			em.persist(ex);
		}
		logger.debug("Adicionando Exemplares: " + exemplares.size());
		em.getTransaction().commit();
	}

	public List<Exemplar> pesquisarExemplaresPorObra(Integer idObra) {
		TypedQuery<Exemplar> query = em.createNamedQuery(Exemplar.NAMED_QUERY_LISTAR_POR_OBRA, Exemplar.class);
		query.setParameter("idObra", idObra);
		return query.getResultList();
	}
}