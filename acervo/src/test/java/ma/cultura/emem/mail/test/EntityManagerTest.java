package ma.cultura.emem.mail.test;

import javax.persistence.EntityManager;

import ma.cultura.emem.acervo.model.ItemAcervo;
import ma.cultura.emem.acervo.model.Usuario;
import ma.cultura.emem.acervo.util.jpa.EntityManagerProducer;

public class EntityManagerTest {
	
	public static void main(String[] args) {
		EntityManagerProducer prod = new EntityManagerProducer();
		EntityManager em = prod.getEntityManager();
		ItemAcervo o = em.find(ItemAcervo.class, 278);
		System.out.println(o);
		
		Usuario u = new Usuario();
		u.setNome("Thiago3");
		u.setCpf("");
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
		
		prod.close(em);
		prod.closeFactory();
	}
}
