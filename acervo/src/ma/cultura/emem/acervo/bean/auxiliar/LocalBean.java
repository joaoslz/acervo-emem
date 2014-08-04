package ma.cultura.emem.acervo.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.modelo.auxiliar.Local;

@Named
@ViewScoped
public class LocalBean extends BaseAuxiliarBean<Local> {

	private static final long serialVersionUID = 1543363740262930139L;

	@Override
	protected Local newEntityInstance() {
		return new Local();
	}

	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
}
