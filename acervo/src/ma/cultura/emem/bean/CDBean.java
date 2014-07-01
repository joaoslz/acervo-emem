
package ma.cultura.emem.bean;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.bean.datamodel.CDLazyDataModel;
import ma.cultura.emem.dao.CDDAO;
import ma.cultura.emem.dao.MidiaDAO;
import ma.cultura.emem.dao.auxiliar.CantorDAO;
import ma.cultura.emem.dao.auxiliar.CompositorDAO;
import ma.cultura.emem.dao.auxiliar.GravadoraDAO;
import ma.cultura.emem.modelo.CD;
import ma.cultura.emem.modelo.Musica;
import ma.cultura.emem.modelo.auxiliar.Cantor;
import ma.cultura.emem.modelo.auxiliar.Compositor;
import ma.cultura.emem.modelo.auxiliar.Gravadora;
import ma.cultura.emem.modelo.auxiliar.Midia;

@Named("cdBean")
@ViewScoped
public class CDBean extends AbstractItemAcervoBean {

	private static final long serialVersionUID = 6482945063096362016L;
	
	@Inject
	private CDLazyDataModel cdLazyDataModel;

	@Inject
	private CDDAO cdDAO;
	@Inject
	private MidiaDAO midiaDAO;
	
	@Inject
	private GravadoraDAO gravadoraDAO;
	@Inject
	private CantorDAO cantorDAO;
	@Inject
	private CompositorDAO compositorDAO;

	private Gravadora gravadoraAdd = new Gravadora();
	private Cantor cantorAdd = new Cantor();
	private Compositor compositorAdd =  new Compositor();
	private Musica musicaAdd = new Musica();
	
	
	@Override
	public void gravar() {
		//se ja possui um id eh uma edicao de livro(autalizacao), senao eh um novo livro sendo cadastrado.
		boolean isEdicao = getItemAcervo().getId() != null;
		itemAcervo = cdDAO.atualizar(getCD());
		logger.debug("id: " + itemAcervo.getId());
		if (!isEdicao) {
			cadastrarExemplares();
		}
		limparForm();
	}
	
	
	public void addMusica(){
		getCD().addMusica(musicaAdd);
		musicaAdd = new Musica();
	}
	
	public void removerMusica(int index) {
		logger.debug("REMOVENDO musica index: " + index);
		if (!this.getCD().getMusicas().isEmpty()) {
			Musica m = this.getCD().getMusicas().remove(index);
			m.setCd(null);
			logger.debug("REMOVIDO DA LISTA: " + m.getTitulo());
		}
	}

	public void gravarCompositor(){
		compositorDAO.adicionar(compositorAdd);
		musicaAdd.getCompositores().add(compositorAdd);
		compositorAdd = new Compositor();
	}
	
	public void gravarCantor(){
		cantorDAO.adicionar(cantorAdd);
		getCD().getCantores().add(cantorAdd);
		cantorAdd = new Cantor();
	}
	
	public void gravarGravadora(){
		gravadoraDAO.adicionar(gravadoraAdd);
		getCD().setGravadora(gravadoraAdd);
		gravadoraAdd = new Gravadora();
	}
	
	@Override
	public CD getNewItemAcervo() {
		return new CD();
	}
	
	public CD getCD(){
		return (CD) getItemAcervo();
	}

	@Override
	public String recarregarPagina() {
		return "cd?faces-redirect=true";
	}
	
	public List<Midia> getlistaMidias(){
		return midiaDAO.findAll();
	}

	public GravadoraDAO getGravadoraDAO() {
		return gravadoraDAO;
	}

	public CantorDAO getCantorDAO() {
		return cantorDAO;
	}

	public CDLazyDataModel getCdLazyDataModel() {
		return cdLazyDataModel;
	}

	public Gravadora getGravadoraAdd() {
		return gravadoraAdd;
	}

	public void setGravadoraAdd(Gravadora gravadoraAdd) {
		this.gravadoraAdd = gravadoraAdd;
	}

	public Cantor getCantorAdd() {
		return cantorAdd;
	}

	public void setCantorAdd(Cantor cantorAdd) {
		this.cantorAdd = cantorAdd;
	}

	public Musica getMusicaAdd() {
		return musicaAdd;
	}

	public void setMusicaAdd(Musica musicaAdd) {
		this.musicaAdd = musicaAdd;
	}

	public CompositorDAO getCompositorDAO() {
		return compositorDAO;
	}


	public Compositor getCompositorAdd() {
		return compositorAdd;
	}


	public void setCompositorAdd(Compositor compositorAdd) {
		this.compositorAdd = compositorAdd;
	}
}