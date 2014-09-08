package ma.cultura.emem.acervo.controller.auxiliar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.Usuario;

@Named
@ViewScoped
public class UsuarioBean extends AuxiliarBean<Usuario> {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Usuario newEntityInstance() {
		return new Usuario();
	}
	
	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
	
	public Usuario getUsuario() {
		return getEntity();
	}
	
	public void validarCPFDuplicado(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null) {
			int size = consultasService.findByProperty("cpf", value.toString()).size();
			if (size > 0) {
				String msg = "Já existe cadastrado para o CPF " + value + "!";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
				throw new ValidatorException(message);
			}
		}
	}
}
