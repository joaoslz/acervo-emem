package ma.cultura.emem.acervo.dao;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;

public class DAOProduces<T> {

	@Produces
	public DAO<T> createDAO(InjectionPoint ip, EntityManager em) {
		ParameterizedType type = (ParameterizedType) ip.getType();
		Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
		return new DAO<T>(classe, em);
	}
}
