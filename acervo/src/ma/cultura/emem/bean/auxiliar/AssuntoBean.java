package ma.cultura.emem.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.modelo.auxiliar.Assunto;

@Named
@ViewScoped
public class AssuntoBean extends BaseAuxiliarBean<Assunto> {

	private static final long serialVersionUID = -3921805135540464775L;

	@Override
	protected Assunto newEntityInstance() {
		return new Assunto();
	}
}
