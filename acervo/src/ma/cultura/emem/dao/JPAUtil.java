package ma.cultura.emem.dao;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil  {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("acervo_emem");

	// Anotacao para que o CDI reconheca este metodo com um produto para o EntityManager
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		EntityManager em = emf.createEntityManager(); 
		return em;
	}
	
	@Produces
	public DAO getDAO(InjectionPoint ip) {
		ParameterizedType type = (ParameterizedType) ip.getType();
		Class classe = (Class) type.getActualTypeArguments()[0];
		System.out.println("getDAO: "+classe);
		return new DAO(classe);
	}

	// Metodo para que o CDI feche o EntityManager ao liberar da memoria.
	public void close(@Disposes EntityManager em) {
		em.close();
	}
}
