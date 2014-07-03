package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Exemplar;

public class ExemplarDAO extends DAO<Exemplar> {

	private static final long serialVersionUID = -558780804432621543L;

	public ExemplarDAO() {
		super(Exemplar.class);
	}

	public void adicionarExemplares(List<Exemplar> exemplares) {
		em.getTransaction().begin();
		logger.debug("Adicionando Exemplares: " + exemplares.size());
		for (Exemplar ex : exemplares) {
			em.persist(ex);
		}
		em.getTransaction().commit();
	}

	public List<Exemplar> findByItemAcervo(Integer idItemAcervo) {
		TypedQuery<Exemplar> query = em.createNamedQuery("Exemplar.findByItemAcervo", Exemplar.class);
		query.setParameter("idItemAcervo", idItemAcervo);
		return query.getResultList();
	}
}