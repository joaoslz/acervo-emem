package ma.cultura.emem.mail.test;

import javax.persistence.EntityManager;

import ma.cultura.emem.acervo.model.ItemAcervo;
import ma.cultura.emem.acervo.util.jpa.EntityManagerProducer;

public class EntityManagerTest {
	
	public static void main(String[] args) {
		EntityManager em = new EntityManagerProducer().getEntityManager();
		ItemAcervo o = em.find(ItemAcervo.class, 278);
		System.out.println(o);
		
		em.close();
	}
}
