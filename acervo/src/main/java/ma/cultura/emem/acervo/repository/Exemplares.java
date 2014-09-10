package ma.cultura.emem.acervo.repository;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.acervo.model.ItemAcervo;

import org.apache.log4j.Logger;

public class Exemplares implements Serializable {
	
	private static final long serialVersionUID = 5208469530887373682L;
	
	@Inject
	private transient Logger logger;
	@Inject
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		// em.clear();
	}
	
	public int countExemplaresDisponiveis(ItemAcervo it) {
		logger.debug("contando exemplares disponíveis... it " + it);
		TypedQuery<Long> query = em.createNamedQuery("Exemplar.countExemplaresDisponiveisByItemAcervo", Long.class);
		query.setParameter("idItemAcervo", it.getId());
		return query.getSingleResult().intValue();
	}
	
	public int countExemplares(ItemAcervo it) {
		logger.debug("contando exemplares... it " + it);
		TypedQuery<Long> query = em.createNamedQuery("Exemplar.countExemplaresByItemAcervo", Long.class);
		query.setParameter("idItemAcervo", it.getId());
		return query.getSingleResult().intValue();
	}
}