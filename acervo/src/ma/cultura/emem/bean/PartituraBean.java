package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.bean.datamodel.PartituraLazyDataModel;
import ma.cultura.emem.dao.GeneroDAO;
import ma.cultura.emem.dao.InstrumentoDAO;
import ma.cultura.emem.modelo.ItemAcervo;
import ma.cultura.emem.modelo.Partitura;
import ma.cultura.emem.modelo.auxiliar.Genero;
import ma.cultura.emem.modelo.auxiliar.Instrumento;

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
		return instrumentoDAO.findByNome(nome);
	}

	public List<Genero> autocompleteGeneroByNome(String nome) {
		return generoDAO.findByNome(nome);
	}
	
	@Override
	protected void showDialogExemplares() {
		//Sebrescrevendo este método para não exibir os exemplares na tela de partituras.
		//por isso ele está em branco, chamando apenas o limparForm.
		limparForm();
	}

	@Override
	protected ItemAcervo getNewItemAcervo() {
		return new Partitura();
	}

	@Override
	public String recarregarPagina() {
		return "partitura?faces-redirect=true";
	}

	public Partitura getPartitura() {
		return (Partitura) getItemAcervo();
	}

	public PartituraLazyDataModel getPartituraLazyDataModel() {
		return partituraLazyDataModel;
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
}