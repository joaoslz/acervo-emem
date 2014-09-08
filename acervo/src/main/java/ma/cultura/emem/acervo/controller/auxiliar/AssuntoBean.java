package ma.cultura.emem.acervo.controller.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.auxiliar.Assunto;

@Named
@ViewScoped
public class AssuntoBean extends AuxiliarBean<Assunto> {
	
	private static final long serialVersionUID = -3921805135540464775L;
	
	@Override
	protected Assunto newEntityInstance() {
		return new Assunto();
	}
	
	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
}
