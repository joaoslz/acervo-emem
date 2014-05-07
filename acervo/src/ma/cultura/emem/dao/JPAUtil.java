package ma.cultura.emem.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("acervo_emem");

	// Anotação para que o CDI reconheca este método com um produto para o EntityManager
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		EntityManager em = emf.createEntityManager(); 
		return em;
	}

	// Método para que o CDI feche o EntityManager ao liberar da memória.
	public void close(@Disposes EntityManager em) {
		em.close();
	}
}
