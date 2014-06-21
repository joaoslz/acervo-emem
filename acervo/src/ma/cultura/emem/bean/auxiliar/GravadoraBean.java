package ma.cultura.emem.bean.auxiliar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.auxiliar.GravadoraDAO;
import ma.cultura.emem.modelo.auxiliar.Gravadora;

import org.primefaces.event.RowEditEvent;

@Named
@ViewScoped
public class GravadoraBean implements Serializable {

	private static final long serialVersionUID = -4804077138249718146L;

	@Inject
	private GravadoraDAO gravadoraDAO;

	private Gravadora gravadora = new Gravadora();
	private List<Gravadora> gravadoras = new ArrayList<Gravadora>();

	public void updateListaGravadoras() {
		gravadoras = gravadoraDAO.findAll();
	}

	public void editGravadora(RowEditEvent event) {
		Gravadora g = (Gravadora) event.getObject();
		gravadoraDAO.atualizar(g);
	}

	public void gravar() {
		gravadoraDAO.adicionar(this.gravadora);
		gravadoras.add(0, gravadora);
		gravadora = new Gravadora();
	}

	public List<Gravadora> getGravadoras() {
		if (gravadoras.isEmpty())
			updateListaGravadoras();
		return gravadoras;
	}

	public Gravadora getGravadora() {
		return gravadora;
	}

	public void setGravadora(Gravadora gravadora) {
		this.gravadora = gravadora;
	}
}