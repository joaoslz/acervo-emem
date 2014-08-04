package ma.cultura.emem.acervo.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.modelo.auxiliar.Midia;

@Named
@ViewScoped
public class MidiaBean extends BaseAuxiliarBean<Midia> {
	private static final long serialVersionUID = 9034944577107257523L;

	@Override
	protected Midia newEntityInstance() {
		return new Midia();
	}

	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
}
