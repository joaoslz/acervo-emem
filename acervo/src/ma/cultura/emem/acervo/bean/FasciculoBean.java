package ma.cultura.emem.acervo.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.bean.datamodel.FasciculoLazyDataModel;
import ma.cultura.emem.acervo.dao.DAO;
import ma.cultura.emem.acervo.modelo.Artigo;
import ma.cultura.emem.acervo.modelo.Fasciculo;
import ma.cultura.emem.acervo.modelo.auxiliar.Autor;
import ma.cultura.emem.acervo.modelo.auxiliar.MesEnum;
import ma.cultura.emem.acervo.modelo.auxiliar.Periodico;

import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class FasciculoBean extends BaseItemAcervoBean<Fasciculo> {

	private static final long serialVersionUID = -8216262637075293980L;
	
	@Inject
	private DAO<Autor> autorDAO;
	@Inject
	private DAO<Periodico> periodicoDAO;

	private FasciculoLazyDataModel fasciculoLazyDataModel;

	private List<Periodico> listaPeriodicos = new ArrayList<Periodico>();
	private Periodico periodico = new Periodico();
	private Artigo artigoAdd = new Artigo();
	private Autor autorAdd = new Autor();


	@Override
	protected Fasciculo getNewItemAcervo() {
		return new Fasciculo();
	}

	public void showDialogCadastroRevista(){
		RequestContext.getCurrentInstance().execute("PF('dlgNovaRevista').show()");
	}
		
	public String recarregarPagina() {
		return "fasciculo?faces-redirect=true";
	}

	public void limparFormArtigo(){
		artigoAdd = new Artigo();
	}

	public void incluirArtigo() {
		getFasciculo().addArtigo(artigoAdd);
		limparFormArtigo();
		RequestContext.getCurrentInstance().execute("PF('dlgAddArtigo').hide()");
	}

	public void removerArtigo(int index) {
		logger.debug("REMOVENDO artigo index: " + index);
		if (!this.getFasciculo().getArtigos().isEmpty()) {
			Artigo a = this.getFasciculo().getArtigos().remove(index);
			a.setFasciculo(null);
			logger.debug("REMOVIDO DA LISTA: " + a.getTitulo());
		}
	}

	public void gravarAutor() {
		autorDAO.adicionar(autorAdd);
		getArtigoAdd().addAutor(autorAdd);
		autorAdd = new Autor();
	}
	
	public List<Periodico> getListaPeriodicos(){
		if(listaPeriodicos.isEmpty())
			listaPeriodicos = periodicoDAO.findAll();
		return listaPeriodicos;
	}
	
	public MesEnum[] getListaMeses(){
		return MesEnum.values();
	}

	public Artigo getArtigoAdd() {
		return artigoAdd;
	}

	public void setArtigoAdd(Artigo artigoAdd) {
		this.artigoAdd = artigoAdd;
	}

	public Fasciculo getFasciculo() {
		return getItemAcervo();
	}

	public Periodico getPeriodico() {
		return periodico;
	}

	public void setPeriodico(Periodico periodico) {
		logger.debug("Set Periodico: " + periodico);
		getFasciculoLazyDataModel().setPeriodico(periodico);
		getFasciculo().setPeriodico(periodico);
		this.periodico = periodico;
	}

	public FasciculoLazyDataModel getFasciculoLazyDataModel() {
		if(fasciculoLazyDataModel == null){
			fasciculoLazyDataModel = new FasciculoLazyDataModel(dao);
		}
		return fasciculoLazyDataModel;
	}

	public Autor getAutorAdd() {
		return autorAdd;
	}

	public void setAutorAdd(Autor autorAdd) {
		this.autorAdd = autorAdd;
	}

	public DAO<Autor> getAutorDAO() {
		return autorDAO;
	}
}