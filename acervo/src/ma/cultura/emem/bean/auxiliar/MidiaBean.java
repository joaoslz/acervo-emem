package ma.cultura.emem.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.modelo.auxiliar.Midia;

@Named
@ViewScoped
public class MidiaBean extends BaseAuxiliarBean<Midia> {
	private static final long serialVersionUID = 9034944577107257523L;

	@Override
	protected Midia newEntityInstance() {
		return new Midia();
	}
}
