package ma.cultura.emem.acervo.repository;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;

public class ConsultasProducer<T> {
	
	@Produces
	public Consultas<T> create(InjectionPoint ip, EntityManager em) {
		ParameterizedType type = (ParameterizedType) ip.getType();
		Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
		return new Consultas<T>(classe, em);
	}
}
