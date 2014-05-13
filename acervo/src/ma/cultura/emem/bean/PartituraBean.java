package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.bean.datamodel.PartituraLazyDataModel;
import ma.cultura.emem.dao.GeneroDAO;
import ma.cultura.emem.dao.InstrumentoDAO;
import ma.cultura.emem.modelo.Genero;
import ma.cultura.emem.modelo.Instrumento;
import ma.cultura.emem.modelo.Obra;
import ma.cultura.emem.modelo.Partitura;

@Named
@ViewScoped
public class PartituraBean extends AbstractObraBean implements Serializable {

	private static final long serialVersionUID = -5763773183540321467L;

	@Inject
	private InstrumentoDAO instrumentoDAO;
	
	@Inject
	private GeneroDAO generoDAO;
	
	@Inject
	private PartituraLazyDataModel partituraLazyDataModel;
	
	private Instrumento instrumentoAdd = new Instrumento();
	private Genero generoAdd = new Genero();

	public PartituraBean() {
	}

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
	
	public List<Instrumento> autocompleteInstrumentoByNome(String nome) {
		return instrumentoDAO.pesquisarPorNome(nome);
	}

	public List<Genero> autocompleteGeneroByNome(String nome) {
		return generoDAO.pesquisarPorNome(nome);
	}
	
	@Override
	protected void showDialogExemplares() {
		//Sebrescrevendo este método para não exibir os exemplares na tela de partituras.
		//por isso ele está em branco, chamando apenas o limparForm.
		limparForm();
	}

	@Override
	protected Obra getNewObra() {
		return new Partitura();
	}

	@Override
	public String recarregarPagina() {
		return "partitura?faces-redirect=true";
	}

	public Partitura getPartitura() {
		return (Partitura) getObra();
	}

	public PartituraLazyDataModel getPartituraLazyDataModel() {
		return partituraLazyDataModel;
	}

	/**
	 * @return the instrumentoAdd
	 */
	public Instrumento getInstrumentoAdd() {
		return instrumentoAdd;
	}

	/**
	 * @param instrumentoAdd the instrumentoAdd to set
	 */
	public void setInstrumentoAdd(Instrumento instrumentoAdd) {
		this.instrumentoAdd = instrumentoAdd;
	}

	/**
	 * @return the generoAdd
	 */
	public Genero getGeneroAdd() {
		return generoAdd;
	}

	/**
	 * @param generoAdd the generoAdd to set
	 */
	public void setGeneroAdd(Genero generoAdd) {
		this.generoAdd = generoAdd;
	}
}