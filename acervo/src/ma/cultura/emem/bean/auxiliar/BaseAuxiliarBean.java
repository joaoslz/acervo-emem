package ma.cultura.emem.bean.auxiliar;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ma.cultura.emem.bean.datamodel.BaseEntityLazyDataModel;
import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.BaseEntity;

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

	public void gravar() {
		try {
			logger.debug("Gravando entity: " + entity.getClass().getSimpleName());
			dao.atualizar(entity);
			entity = newEntityInstance();
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			FacesContext.getCurrentInstance().addMessage(e.getMessage(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar", e.getMessage()));
		}
	}

	public void edit(RowEditEvent event) {
		logger.debug("Editando entity: " + entity.getClass().getSimpleName());
		final T t = (T) event.getObject();
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