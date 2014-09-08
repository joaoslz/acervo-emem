package ma.cultura.emem.acervo.controller.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.auxiliar.Autor;

@Named
@ViewScoped
public class AutorBean extends AuxiliarBean<Autor> {
	
	private static final long serialVersionUID = 4648277529025864150L;
	
	@Override
	protected Autor newEntityInstance() {
		return new Autor();
	}
	
	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
	
}
