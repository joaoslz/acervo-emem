package ma.cultura.emem.produces;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProduces  {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("acervo_emem");
	
	// Anotacao para que o CDI reconheca este metodo com um produto para o EntityManager
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		EntityManager em = emf.createEntityManager(); 
		return em;
	}

	// Metodo para que o CDI feche o EntityManager ao liberar da memoria.
	public void close(@Disposes EntityManager em) {
		em.close();
	}
}
