package ma.cultura.emem.acervo.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.modelo.auxiliar.Instrumento;

@Named
@ViewScoped
public class InstrumentoBean extends BaseAuxiliarBean<Instrumento> {

	private static final long serialVersionUID = 877217095168912389L;

	@Override
	protected Instrumento newEntityInstance() {
		return new Instrumento();
	}
}
