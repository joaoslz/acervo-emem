package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.bean.datamodel.PeriodicoLazyDataModel;
import ma.cultura.emem.modelo.Artigo;
import ma.cultura.emem.modelo.Autor;
import ma.cultura.emem.modelo.Fasciculo;
import ma.cultura.emem.modelo.Obra;
import ma.cultura.emem.modelo.PeriodicidadeEnum;
import ma.cultura.emem.modelo.Periodico;

import org.apache.log4j.Logger;

@Named
@ViewScoped
public class PeriodicoBean extends AbstractObraBean implements Serializable {

	private static final long serialVersionUID = -8216262637075293980L;

	@Inject
	private Logger logger;

	// DAOs
//	@Inject
//	private PeriodicoDAO periodicoDAO;

	@Inject
	private PeriodicoLazyDataModel periodicoLazyDataModel;

	private Fasciculo fasciculoAdd = new Fasciculo();

	public PeriodicoBean() {
	}

//	public void adicionarArtigo() {
//		getPeriodico().adicionaArtigo(artigoAdd);
//		artigoAdd = new Artigo();
//	}
//
//	public void removerArtigo() {
//		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//		int index = Integer.parseInt(params.get("index").toString());
//		logger.debug("REMOVENDO artigo index: " + index);
//		if (!getPeriodico().getArtigos().isEmpty()) {
//			Artigo a = getPeriodico().getArtigos().remove(index);
//			a.setPeriodico(null);
//			logger.debug("REMOVIDO DA LISTA: " + a.getTitulo());
//		}
//	}
//	
//	public void limparFormArtigo(){
//		artigoAdd = new Artigo();
//	}
//	
//	public void gravarAutorArtigo() {
//		autorAdd.setEhAutorArtigo(true);
//		autorDAO.adicionar(autorAdd);
//		getArtigoAdd().adicionarAutor(autorAdd);
//		autorAdd = new Autor();
//	}
//	
//	public List<Autor> autocompleteAutorArtigoByNome(String nome) {
//		return autorDAO.pesquisarPorNomeAutorArtigo(nome);
//	}
	

	@Override
	protected void showDialogExemplares() {
		//Sebrescrevendo este método para não exibir os exemplares na tela de periódicos.
		//por isso ele está em branco, chamando apenas o limparForm.
		limparForm();
	}

	@Override
	protected Obra getNewObra() {
		return new Periodico();
	}

	@Override
	public String recarregarPagina() {
		return "periodico?faces-redirect=true";
	}

	public Periodico getPeriodico() {
		return (Periodico) getObra();
	}
	
	public PeriodicidadeEnum[] getListaPeriodicidade(){
		return PeriodicidadeEnum.values();
	}


	public PeriodicoLazyDataModel getPeriodicoLazyDataModel() {
		return periodicoLazyDataModel;
	}

	public Fasciculo getFasciculoAdd() {
		return fasciculoAdd;
	}

	public void setFasciculoAdd(Fasciculo fasciculoAdd) {
		this.fasciculoAdd = fasciculoAdd;
	}

}