package ma.cultura.emem.acervo.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.modelo.auxiliar.Arranjador;

@Named
@ViewScoped
public class ArranjadorBean extends BaseAuxiliarBean<Arranjador> {

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
