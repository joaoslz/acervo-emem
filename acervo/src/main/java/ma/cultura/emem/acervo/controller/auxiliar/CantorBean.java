package ma.cultura.emem.acervo.controller.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.auxiliar.Cantor;

@Named
@ViewScoped
public class CantorBean extends AuxiliarBean<Cantor> {

	private static final long serialVersionUID = -2191060285398179701L;

	@Override
	protected Cantor newEntityInstance() {
		return new Cantor();
	}

	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
}
