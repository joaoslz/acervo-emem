package ma.cultura.emem.bean.auxiliar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.MidiaDAO;
import ma.cultura.emem.modelo.auxiliar.Midia;

import org.primefaces.event.RowEditEvent;

@Named
@ViewScoped
public class MidiaBean implements Serializable {

	private static final long serialVersionUID = -6115661171344809737L;
	
	@Inject
	private MidiaDAO midiaDAO;

	private Midia midia = new Midia();
	private List<Midia> midias = new ArrayList<Midia>();

	public void updateListaMidias() {
		midias = midiaDAO.findAll();
	}

	public void editMidia(RowEditEvent event) {
		Midia m = (Midia) event.getObject();
		midiaDAO.atualizar(m);
	}

	public void gravar() {
		midiaDAO.adicionar(this.midia);
		midias.add(0, midia);
		midia = new Midia();
	}

	public List<Midia> getMidias() {
		if (midias.isEmpty())
			updateListaMidias();
		return midias;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}
}
