package ma.cultura.emem.dao;

import javax.persistence.EntityManager;

import ma.cultura.emem.modelo.CD;
import ma.cultura.emem.modelo.Musica;

public class PopulaBDEmem {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		CD cd  = new CD();
		cd.setTitulo("teste");
		
		Musica m = new Musica();
		m.setTitulo("musica 1");
		
		cd.addMusica(m);

		em.persist(cd);
		
		em.close();

	}
}
