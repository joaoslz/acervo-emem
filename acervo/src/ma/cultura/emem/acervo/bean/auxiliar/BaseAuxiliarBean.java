package ma.cultura.emem.acervo.bean.auxiliar;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import ma.cultura.emem.acervo.bean.datamodel.BaseEntityLazyDataModel;
import ma.cultura.emem.acervo.dao.DAO;
import ma.cultura.emem.acervo.modelo.BaseEntity;
import ma.cultura.emem.acervo.util.jpa.Transactional;
import ma.cultura.emem.acervo.util.jsf.FacesMessages;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

public abstract class BaseAuxiliarBean<T extends BaseEntity> implements Serializable {

	private static final long serialVersionUID = 3847398251913840437L;

	@Inject
	protected Logger logger;
	@Inject
	protected FacesMessages facesMsg;

	@Inject
	protected DAO<T> dao;

	private BaseEntityLazyDataModel<T> lazyDataModel;

	private T entity;

	protected abstract T newEntityInstance();

	protected abstract String getNomeEntity();

	@PostConstruct
	public void init(){
		logger.debug("init: " + getClass().getSimpleName());
		lazyDataModel = new BaseEntityLazyDataModel<T>(dao);
	}

	public void validarNomeDuplicado(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value != null){
			int size = dao.getByNome(value.toString()).size();
			if (size > 0) {
				String msg = "Já existe um cadastrado com o nome " + value + "!";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
				throw new ValidatorException(message);
			}
		}
	}

	@Transactional
	public void gravar() {
		logger.debug("Gravando entity: " + entity.getClass().getSimpleName());
		dao.atualizar(entity);
		entity = newEntityInstance();
		//		}catch(ConstraintException exc){
		//			//FIXME para solucionar a incompatibilidade com o mojarra 2.2.6
		//			//Não atualizei o mojarra pois ele está dando problema com o @ViewScoped
		//			for(ConstraintViolation cv: exc.getConstraintViolations()){
		//				facesMsg.error(cv.getPropertyPath().toString(), cv.getMessageTemplate());
		//				System.out.println("... " + cv);
		//			}
		//			throw new RuntimeException(exc);
		//		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void edit(RowEditEvent event) {
		logger.debug("Editando entity: " + entity.getClass().getSimpleName());
		T t = (T) event.getObject();
		dao.atualizar(t);
	}

	public BaseEntityLazyDataModel<T> getLazyDataModel() {
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