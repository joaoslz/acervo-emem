package ma.cultura.emem.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("acervo_emem");

    public EntityManager getEntityManager() {
	return emf.createEntityManager();
    }

    public void close(EntityManager em) {
	em.close();
    }
}
