package ma.cultura.emem.acervo.bean.auxiliar;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import ma.cultura.emem.acervo.bean.datamodel.BaseEntityLazyDataModel;
import ma.cultura.emem.acervo.dao.DAO;
import ma.cultura.emem.acervo.modelo.BaseEntity;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

public abstract class BaseAuxiliarBean<T extends BaseEntity> implements Serializable {

	private static final long serialVersionUID = 3847398251913840437L;

	@Inject
	protected Logger logger;

	@Inject
	protected DAO<T> dao;

	private BaseEntityLazyDataModel<T> lazyDataModel;

	private T entity;

	protected abstract T newEntityInstance();

	protected abstract String getNomeEntity();

	public void validarNomeDuplicado(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		int size = dao.getByNome(value.toString()).size();
		if (size > 0) {
			String msg = "Já existe um cadastrado com o nome " + value + "!";
			FacesMessage message = new FacesMessage(msg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

	public void gravar() {
		logger.debug("Gravando entity: " + entity.getClass().getSimpleName());
		dao.atualizar(entity);
		entity = newEntityInstance();
	}

	@SuppressWarnings("unchecked")
	public void edit(RowEditEvent event) {
		logger.debug("Editando entity: " + entity.getClass().getSimpleName());
		T t = (T) event.getObject();
		dao.atualizar(t);
	}

	public BaseEntityLazyDataModel<T> getLazyDataModel() {
		if (lazyDataModel == null) {
			logger.debug("instanciando o lazyDataModel");
			lazyDataModel = new BaseEntityLazyDataModel<T>(dao);
		}
		return lazyDataModel;
	}

	public T getEntity() {
		if (entity == null) {
			entity = newEntityInstance();
		}
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
}