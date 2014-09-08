package ma.cultura.emem.acervo.controller.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.auxiliar.Arranjador;

@Named
@ViewScoped
public class ArranjadorBean extends AuxiliarBean<Arranjador> {

	private static final long serialVersionUID = -5484083358293027730L;

	@Override
	protected Arranjador newEntityInstance() {
		return new Arranjador();
	}

	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
}
