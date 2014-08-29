package ma.cultura.emem.acervo.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.modelo.Usuario;

@Named
@ViewScoped
public class UsuarioBean extends BaseAuxiliarBean<Usuario> {

	private static final long serialVersionUID = 1L;

	@Override
	protected Usuario newEntityInstance() {
		return new Usuario();
	}

	@Override
	protected String getNomeEntity() {
		return getEntity().getNome();
	}
	
	public Usuario getUsuario(){
		return getEntity();
	}
}
