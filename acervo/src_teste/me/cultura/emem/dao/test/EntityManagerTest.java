package me.cultura.emem.dao.test;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(WeldJUnit4Runner.class)
public class EntityManagerTest {

	@Inject 
	EntityManager em;

	@Test
	public void findAllTipoObraTest() {
		int size = em.createQuery("from TipoObra").getResultList().size();
		Assert.assertEquals(2, size);
	}
}