package ma.cultura.emem.bean.auxiliar;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ma.cultura.emem.bean.datamodel.BaseEntityLazyDataModel;
import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.auxiliar.BaseAuxiliarEntity;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

public abstract class BaseAuxiliarBean<T extends BaseAuxiliarEntity> implements Serializable {

	private static final long serialVersionUID = 3847398251913840437L;

	@Inject
	protected Logger logger;

	@Inject
	protected DAO<T> dao;

	private BaseEntityLazyDataModel<T> lazyDataModel;

	private T entity;

	protected abstract T newEntityInstance();

	public void gravar() {
		if (dao.getByNome(getEntity().getNome()).size() <= 0) {
			logger.debug("Gravando entity: " + entity.getClass().getSimpleName());
			dao.atualizar(entity);
			entity = newEntityInstance();
		} else {
			String msg = "Já existe um cadastrado de " + entity.getClass().getSimpleName() + " com o nome " + entity.getNome() + "!";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, ""));
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