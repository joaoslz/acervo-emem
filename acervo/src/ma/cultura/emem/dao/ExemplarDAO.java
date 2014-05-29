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
		for (Exemplar ex : exemplares) {
			em.persist(ex);
		}
		logger.debug("Adicionando Exemplares: " + exemplares.size());
		em.getTransaction().commit();
	}

	public List<Exemplar> pesquisarExemplaresPorItemAcervo(Integer idItemAcervo) {
		TypedQuery<Exemplar> query = em.createNamedQuery("Exemplar.listarPorItemAcervo", Exemplar.class);
		query.setParameter("idItemAcervo", idItemAcervo);
		return query.getResultList();
	}
}