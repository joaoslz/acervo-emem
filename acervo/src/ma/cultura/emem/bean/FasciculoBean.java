package ma.cultura.emem.bean;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.bean.datamodel.FasciculoLazyDataModel;
import ma.cultura.emem.dao.FasciculoDAO;
import ma.cultura.emem.dao.PeriodicoDAO;
import ma.cultura.emem.dao.auxiliar.AutorDAO;
import ma.cultura.emem.modelo.Artigo;
import ma.cultura.emem.modelo.Fasciculo;
import ma.cultura.emem.modelo.ItemAcervo;
import ma.cultura.emem.modelo.Periodico;
import ma.cultura.emem.modelo.auxiliar.Autor;

@Named
@ViewScoped
public class FasciculoBean extends AbstractItemAcervoBean {

	private static final long serialVersionUID = -8216262637075293980L;
	
	@Inject
	private AutorDAO autorDAO;
	@Inject
	private FasciculoDAO fasciculoDAO;
	@Inject
	private PeriodicoDAO periodicoDAO;
	@Inject
	private FasciculoLazyDataModel fasciculoLazyDataModel;

	private Periodico periodico = new Periodico();
	private Artigo artigoAdd = new Artigo();
	private Autor autorAdd = new Autor();


	@Override
	protected ItemAcervo getNewItemAcervo() {
		return new Fasciculo();
	}

//	public void gravar() {
//		boolean isEdicao = getFasciculo().getId() != null;
//		if (isEdicao) {
//			fasciculo = fasciculoDAO.atualizar(getFasciculo());
//			limparForm();
//		}else{
//			fasciculo = fasciculoDAO.adicionar(getFasciculo());
//		}
//	}
	
	public String getStringBotaoGravar(){
		if(getFasciculo().getId() == null)
			return "Gravar";
		else
			return "Gravar Alterações";
	}
	
	public String recarregarPagina() {
		return "fasciculo?faces-redirect=true";
	}

//	public void limparFormArtigo(){
//		artigoAdd = new Artigo();
//	}
//
//	public void adicionarArtigo() {
//		fasciculo.addArtigo(artigoAdd);
//		artigoAdd = new Artigo();
//	}
//
//	public void removerArtigo() {
//		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//		int index = Integer.parseInt(params.get("index").toString());
//		logger.debug("REMOVENDO artigo index: " + index);
//		if (!this.fasciculo.getArtigos().isEmpty()) {
//			Artigo a = this.fasciculo.getArtigos().remove(index);
//			a.setFasciculo(null);
//			logger.debug("REMOVIDO DA LISTA: " + a.getTitulo());
//		}
//	}
//	
//	public void gravarAutor() {
//		autorDAO.adicionar(autorAdd);
//		getArtigoAdd().addAutor(autorAdd);
//		autorAdd = new Autor();
//	}
//	
//	public List<Autor> autocompleteAutorByNome(String nome) {
//		List<Autor> autores = autorDAO.findByNome(nome);
//		//XXX Talvez seja interessante fazer essa exclusão via query. ???
//		if(!getArtigoAdd().getAutores().isEmpty())
//			autores.removeAll(getArtigoAdd().getAutores());		
//		return autorDAO.findByNome(nome);
//	}

	public List<Periodico> autocompletePeriodicoByNome(String nome) {
		return periodicoDAO.findByNome(nome);
	}
	

	public Artigo getArtigoAdd() {
		return artigoAdd;
	}

	public void setArtigoAdd(Artigo artigoAdd) {
		this.artigoAdd = artigoAdd;
	}

	public Fasciculo getFasciculo() {
		return (Fasciculo) getItemAcervo();
	}

	public Periodico getPeriodico() {
		return periodico;
	}

	public void setPeriodico(Periodico periodico) {
		logger.debug("Set Periodico: " + periodico);
		fasciculoLazyDataModel.setPeriodico(periodico);
		getFasciculo().setPeriodico(periodico);
		this.periodico = periodico;
	}

	public FasciculoLazyDataModel getFasciculoLazyDataModel() {
		return fasciculoLazyDataModel;
	}

	public void setFasciculoLazyDataModel(FasciculoLazyDataModel fasciculoLazyDataModel) {
		this.fasciculoLazyDataModel = fasciculoLazyDataModel;
	}
}