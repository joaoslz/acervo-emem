package ma.cultura.emem.dao;

import javax.persistence.EntityManager;

import ma.cultura.emem.modelo.Obra;

public class PopulaBDEmem {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		for(Obra l: em.createQuery("from Obra a order by a.id desc", Obra.class).getResultList()){
			System.out.println(l.getClass().getSimpleName());
		}
		em.close();

	}
}
