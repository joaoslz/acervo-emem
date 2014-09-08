package ma.cultura.emem.acervo.controller.datamodel;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;

import ma.cultura.emem.acervo.model.RootEntity;
import ma.cultura.emem.acervo.repository.ConsultasRepository;
import ma.cultura.emem.acervo.service.ConsultasService;

public class RootEntityLazeDataModelProduces<T extends RootEntity> {
	
	@Produces
	public RootEntityLazyDataModel<T> create(InjectionPoint ip, EntityManager em) {
		ParameterizedType type = (ParameterizedType) ip.getType();
		Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
		ConsultasRepository<T> repository = new ConsultasRepository<>(classe, em);
		ConsultasService<T> service = new ConsultasService<>(repository);
		return new RootEntityLazyDataModel<>(service);
	}
}
