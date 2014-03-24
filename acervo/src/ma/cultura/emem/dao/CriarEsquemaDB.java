package ma.cultura.emem.dao;

import javax.persistence.EntityManager;

public class CriarEsquemaDB {

    public static void main(String[] args) {

	EntityManager em = JPAUtil.getInstance().getEntityManager();
	em.close();
    }
}
