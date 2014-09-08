package ma.cultura.emem.acervo.controller.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.auxiliar.Idioma;

@Named
@ViewScoped
public class IdiomaBean extends AuxiliarBean<Idioma> {

	private static final long serialVersionUID = -6317216060895045412L;

	@Override
	protected Idioma newEntityInstance() {
		return new Idioma();
	}

	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
}
