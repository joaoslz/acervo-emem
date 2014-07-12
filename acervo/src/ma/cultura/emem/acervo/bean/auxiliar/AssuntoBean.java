package ma.cultura.emem.acervo.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.modelo.auxiliar.Assunto;

@Named
@ViewScoped
public class AssuntoBean extends BaseAuxiliarBean<Assunto> {

	private static final long serialVersionUID = -3921805135540464775L;

	@Override
	protected Assunto newEntityInstance() {
		return new Assunto();
	}
}
