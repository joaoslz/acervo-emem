package ma.cultura.emem.acervo.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.modelo.auxiliar.Genero;

@Named
@ViewScoped
public class GeneroBean extends BaseAuxiliarBean<Genero> {

	private static final long serialVersionUID = 877217095168912389L;

	@Override
	protected Genero newEntityInstance() {
		return new Genero();
	}
}
