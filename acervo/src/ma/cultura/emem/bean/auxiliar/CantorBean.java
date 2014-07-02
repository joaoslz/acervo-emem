package ma.cultura.emem.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.modelo.auxiliar.Cantor;

@Named
@ViewScoped
public class CantorBean extends BaseAuxiliarBean<Cantor> {

	private static final long serialVersionUID = -2191060285398179701L;

	@Override
	protected Cantor newEntityInstance() {
		return new Cantor();
	}
}
