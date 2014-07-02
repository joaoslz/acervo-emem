package ma.cultura.emem.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.modelo.auxiliar.Autor;

@Named
@ViewScoped
public class AutorBean extends BaseAuxiliarBean<Autor> {

	private static final long serialVersionUID = 4648277529025864150L;

	@Override
	protected Autor newEntityInstance() {
		return new Autor();
	}
}
