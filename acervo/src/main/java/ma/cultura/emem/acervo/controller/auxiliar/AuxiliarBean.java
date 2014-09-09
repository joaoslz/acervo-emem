package ma.cultura.emem.acervo.controller.auxiliar;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import ma.cultura.emem.acervo.controller.datamodel.RootEntityLazyDataModel;
import ma.cultura.emem.acervo.model.auxiliar.AuxiliarEntity;
import ma.cultura.emem.acervo.service.CRUDService;
import ma.cultura.emem.acervo.service.ConsultasService;
import ma.cultura.emem.acervo.util.jsf.FacesMessages;
import ma.cultura.emem.acervo.util.jsf.FacesUtil;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

public abstract class AuxiliarBean<T extends AuxiliarEntity> implements Serializable {
	
	private static final long serialVersionUID = 3847398251913840437L;
	
	@Inject
	protected Logger logger;
	@Inject
	protected FacesMessages facesMsg;
	
	@Inject
	protected CRUDService crudService;
	@Inject
	protected ConsultasService<T> consultasService;
	
	@Inject
	private RootEntityLazyDataModel<T> lazyDataModel;
	
	private T entity;
	//Esta entity será utilizada para transferencia deste bean para outro bean, via setPropertyActionListener.
	private T entityTransfer;
	
	private String entityName;
	
	protected abstract T newEntityInstance();
	
	protected abstract String getNomeEntity();
	
	@PostConstruct
	public void init() {
		entity = newEntityInstance();
		entityName = entity.getClass().getSimpleName();
		logger.debug("init: " + entityName);
	}
	
	public void validarNomeDuplicado(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null) {
			int size = consultasService.findByNome(value.toString()).size();
			if (size > 0) {
				String msg = "Já existe um cadastrado com o nome " + value + "!";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
				throw new ValidatorException(message);
			}
		}
	}
	
	public void gravar() {
		logger.debug("Gravando entity: " + entityName);
		crudService.atualizar(entity);
		entity = newEntityInstance();
	}
	
	public void gravarViaDialog() {
		logger.debug("Gravando via dialog: " + entityName);
		//Entidade que será enviada para outro ManagedBean
		entityTransfer = (T) crudService.atualizar(entity);
		//Limpa o formulário do dialog.
		entity = newEntityInstance();
		// Fecha o Dialog aqui para evitar bug na validação.
		FacesUtil.hideDialog("dlg" + entityName);
		logger.debug("hide dialog: dlg" + entityName);
	}
	
	public void edit(RowEditEvent event) {
		T t = (T) event.getObject();
		logger.debug("Editando entity: " + t.getClass().getSimpleName());
		crudService.atualizar(t);
	}
	
	public RootEntityLazyDataModel<T> getLazyDataModel() {
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

	public T getEntityTransfer() {
		return entityTransfer;
	}

	public void setEntityTransfer(T entityTransfer) {
		this.entityTransfer = entityTransfer;
	}
}