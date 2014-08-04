package ma.cultura.emem.acervo.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.modelo.auxiliar.Editora;

@Named
@ViewScoped
public class EditoraBean extends BaseAuxiliarBean<Editora> {

	private static final long serialVersionUID = 5824910301354118727L;

	@Override
	protected Editora newEntityInstance() {
		return new Editora();
	}
	
	public Editora getEditora(){
		return getEntity();
	}

	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
}
