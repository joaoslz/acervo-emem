package ma.cultura.emem.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.modelo.auxiliar.Editora;

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
}
