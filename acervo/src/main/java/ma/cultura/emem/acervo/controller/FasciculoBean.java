package ma.cultura.emem.acervo.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.controller.datamodel.FasciculoLazyDataModel;
import ma.cultura.emem.acervo.model.Artigo;
import ma.cultura.emem.acervo.model.Fasciculo;
import ma.cultura.emem.acervo.model.auxiliar.Autor;
import ma.cultura.emem.acervo.model.auxiliar.Periodico;
import ma.cultura.emem.acervo.model.auxiliar.enums.MesEnum;

import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class FasciculoBean extends ItemAcervoBean<Fasciculo> {
	
	private static final long serialVersionUID = -8216262637075293980L;
	
	@Inject
	private FasciculoLazyDataModel fasciculoLazyDataModel;
	
	private Periodico periodico = new Periodico();
	private Artigo artigoAdd = new Artigo();
	
	@Override
	protected Fasciculo getNewItemAcervo() {
		return new Fasciculo();
	}
	
	public void showDialogCadastroRevista() {
		RequestContext.getCurrentInstance().execute("PF('dlgNovaRevista').show()");
	}
	
	public String recarregarPagina() {
		return "fasciculo?faces-redirect=true";
	}
	
	// .............................................CADASTRO DE ARTIGO > > > >
	public void limparFormArtigo() {
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
	
	public void setAutor(Autor a) {
		getArtigoAdd().addAutor(a);
	}
	
	public Artigo getArtigoAdd() {
		return artigoAdd;
	}
	
	public void setArtigoAdd(Artigo artigoAdd) {
		this.artigoAdd = artigoAdd;
	}
	
	// ...........................................CADASTRO DE ARTIGO < < < < <
	
	public Periodico getPeriodico() {
		return periodico;
	}
	
	public void setPeriodico(Periodico periodico) {
		logger.debug("Set Periodico: " + periodico);
		getFasciculoLazyDataModel().setPeriodico(periodico);
		getFasciculo().setPeriodico(periodico);
		this.periodico = periodico;
	}
	
	public List<Periodico> getListaPeriodicos() {
		return itemAcervoService.findAllPeriodicos();
	}
	
	public MesEnum[] getListaMeses() {
		return MesEnum.values();
	}
	
	public Fasciculo getFasciculo() {
		return getEntity();
	}
	
	public FasciculoLazyDataModel getFasciculoLazyDataModel() {
		return fasciculoLazyDataModel;
	}
}