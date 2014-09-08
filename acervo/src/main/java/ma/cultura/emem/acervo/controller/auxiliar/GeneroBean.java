package ma.cultura.emem.acervo.controller.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.auxiliar.Genero;

@Named
@ViewScoped
public class GeneroBean extends AuxiliarBean<Genero> {
	
	private static final long serialVersionUID = 877217095168912389L;
	
	@Override
	protected Genero newEntityInstance() {
		return new Genero();
	}
	
	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
}
