package ma.cultura.emem.acervo.controller.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.auxiliar.Editora;

@Named
@ViewScoped
public class EditoraBean extends AuxiliarBean<Editora> {
	
	private static final long serialVersionUID = 5824910301354118727L;
	
	@Override
	protected Editora newEntityInstance() {
		return new Editora();
	}
	
	public Editora getEditora() {
		return getEntity();
	}
	
	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
}
