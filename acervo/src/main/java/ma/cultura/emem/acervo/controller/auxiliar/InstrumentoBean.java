package ma.cultura.emem.acervo.controller.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.auxiliar.Instrumento;

@Named
@ViewScoped
public class InstrumentoBean extends AuxiliarBean<Instrumento> {

	private static final long serialVersionUID = 877217095168912389L;

	@Override
	protected Instrumento newEntityInstance() {
		return new Instrumento();
	}

	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
}
