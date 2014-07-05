package ma.cultura.emem.produces;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;

import ma.cultura.emem.dao.DAO;

public class DAOProduces<T> {

	@Produces
	public DAO<T> createDAO(InjectionPoint ip, EntityManager em) {
		ParameterizedType type = (ParameterizedType) ip.getType();
		Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
		System.out.println("...............createDAO: "+classe);
		return new DAO<T>(classe, em);
	}
}
