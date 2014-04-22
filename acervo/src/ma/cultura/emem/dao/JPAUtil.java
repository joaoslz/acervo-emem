package ma.cultura.emem.dao;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class JPAUtil {
	private static final Logger LOGGER = Logger.getLogger(JPAUtil.class);
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("acervo_emem");

	// Anota��o para que o CDI reconheca este m�todo com um produto para o EntityManager
	@Produces
	public EntityManager getEntityManager() {
		EntityManager em = emf.createEntityManager(); 
		LOGGER.debug("Criando EntityManager >> " + em.hashCode());
		return em;
	}

	// M�todo para que o CDI feche o EntityManager ao liberar da mem�ria.
	public void close(@Disposes EntityManager em) {
		LOGGER.debug("Fechando EntityManager >> " + em.hashCode());
		em.close();
	}
}
