package ma.cultura.emem.acervo.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

public class CRUDRepository implements Serializable {
	
	private static final long serialVersionUID = -4361432740747336731L;
	
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	
	public void adicionar(Object t) {
		logger.debug("persist " + t);
		em.persist(t);
	}
	
	public void adicionarLote(List lote) {
		logger.debug("Quantidade : " + lote.size());
		for (Object t : lote) {
			em.persist(t);
		}
	}
	
	public Object atualizar(Object t) {
		logger.debug("merge: " + t);
		return em.merge(t);
	}
	
	public void remover(Object t) {
		logger.debug("remove " + t);
		em.remove(em.merge(t));
	}
	
}