package ma.cultura.emem.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.modelo.auxiliar.Gravadora;

@Named
@ViewScoped
public class GravadoraBean extends BaseAuxiliarBean<Gravadora> {

	private static final long serialVersionUID = 877217095168912389L;

	@Override
	protected Gravadora newEntityInstance() {
		return new Gravadora();
	}
}
