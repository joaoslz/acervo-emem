package ma.cultura.emem.acervo.service;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;

import ma.cultura.emem.acervo.model.auxiliar.AuxiliarEntity;
import ma.cultura.emem.acervo.repository.ConsultasRepository;

public class ConsultasServiceProducer<T extends AuxiliarEntity> {

	@Produces
	public ConsultasService<T> create(InjectionPoint ip, EntityManager em) {
		ParameterizedType type = (ParameterizedType) ip.getType();
		Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
		ConsultasRepository<T> repository = new  ConsultasRepository<T>(classe, em);
		return new ConsultasService(repository);
	}
}
