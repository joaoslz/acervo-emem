package ma.cultura.emem.bean.auxiliar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.modelo.auxiliar.Compositor;

@Named
@ViewScoped
public class CompositorBean extends BaseAuxiliarBean<Compositor> {
	private static final long serialVersionUID = -4782111405292082858L;

	@Override
	protected Compositor newEntityInstance() {
		return new Compositor();
	}
}
