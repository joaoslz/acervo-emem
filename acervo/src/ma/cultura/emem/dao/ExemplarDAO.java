package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import ma.cultura.emem.modelo.Exemplar;
import ma.cultura.emem.modelo.Obra;

public class ExemplarDAO extends DAO<Exemplar> {

	private static final Logger LOGGER = Logger.getLogger(ExemplarDAO.class);

	public ExemplarDAO() {
		super(Exemplar.class);
	}

	public void merge(Exemplar exemplar) {
		EntityManager em = JPAUtil.getInstance().getEntityManager();
		em.getTransaction().begin();
		em.merge(exemplar);
		em.getTransaction().commit();
		em.close();
	}

	public void cadastrarExemplares(List<Exemplar> exemplares) {
		EntityManager em = JPAUtil.getInstance().getEntityManager();
		em.getTransaction().begin();
		for (Exemplar ex : exemplares) {
			em.persist(ex);
		}
		em.getTransaction().commit();
		em.close();
	}

	public List<Exemplar> listarExemplaresByLivroId(Integer idObra) {
		EntityManager em = JPAUtil.getInstance().getEntityManager();
		TypedQuery<Exemplar> query = em.createQuery("from Exemplar e where e.obra.id = :idObra", Exemplar.class);
		query.setParameter("idObra", idObra);
		List<Exemplar> exemplares = query.getResultList();
		em.close();
		return exemplares;
	}
}