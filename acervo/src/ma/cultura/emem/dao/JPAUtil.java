package ma.cultura.emem.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("acervo_emem");
    
    private static JPAUtil instance = null;
    
    private JPAUtil(){ }

    public static JPAUtil getInstance(){
	if(instance == null)
	    instance = new JPAUtil();
	return instance;
    }
    
    public EntityManager getEntityManager() {
	return emf.createEntityManager();
    }
}
