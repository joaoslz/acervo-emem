package ma.cultura.emem.dao;

import javax.persistence.EntityManager;

public class CriarEsquemaDB {

    public static void main(String[] args) {

	JPAUtil jpaUtil = new JPAUtil();
	EntityManager em = jpaUtil.getEntityManager();

	jpaUtil.close(em);

    }

}
