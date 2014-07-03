package ma.cultura.emem.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.Partitura;
import ma.cultura.emem.modelo.auxiliar.Genero;
import ma.cultura.emem.modelo.auxiliar.Instrumento;

@Named
@ViewScoped
public class PartituraBean extends AbstractItemAcervoBean<Partitura> implements Serializable {

	private static final long serialVersionUID = -5763773183540321467L;

	@Inject
	private DAO<Instrumento> instrumentoDAO;
	@Inject
	private DAO<Genero> generoDAO;
	
	private Instrumento instrumentoAdd = new Instrumento();
	private Genero generoAdd = new Genero();


	public void gravarInstrumento() {
		instrumentoDAO.adicionar(instrumentoAdd);
		getPartitura().setInstrumento(instrumentoAdd);
		instrumentoAdd = new Instrumento();
	}

	public void gravarGenero() {
		generoDAO.adicionar(generoAdd);
		getPartitura().setGenero(generoAdd);
		generoAdd = new Genero();
	}
		
	@Override
	protected void showDialogExemplares() {
		/*Sebrescrevendo este método para não exibir os exemplares na tela de partituras.
		  por isso ele está em branco, chamando apenas o limparForm.*/
		limparForm();
	}

	@Override
	protected Partitura getNewItemAcervo() {
		return new Partitura();
	}

	@Override
	public String recarregarPagina() {
		return "partitura?faces-redirect=true";
	}

	public Partitura getPartitura() {
		return getItemAcervo();
	}

	public Instrumento getInstrumentoAdd() {
		return instrumentoAdd;
	}

	public void setInstrumentoAdd(Instrumento instrumentoAdd) {
		this.instrumentoAdd = instrumentoAdd;
	}

	public Genero getGeneroAdd() {
		return generoAdd;
	}

	public void setGeneroAdd(Genero generoAdd) {
		this.generoAdd = generoAdd;
	}

	public DAO<Instrumento> getInstrumentoDAO() {
		return instrumentoDAO;
	}

	public DAO<Genero> getGeneroDAO() {
		return generoDAO;
	}
}