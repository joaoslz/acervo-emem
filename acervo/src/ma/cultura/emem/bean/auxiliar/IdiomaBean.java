package ma.cultura.emem.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.modelo.auxiliar.Idioma;

@Named
@ViewScoped
public class IdiomaBean extends BaseAuxiliarBean<Idioma> {

	private static final long serialVersionUID = -6317216060895045412L;

	@Override
	protected Idioma newEntityInstance() {
		return new Idioma();
	}
}
