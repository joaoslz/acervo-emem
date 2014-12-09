package ma.cultura.emem.acervo.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.CD;
import ma.cultura.emem.acervo.model.Musica;
import ma.cultura.emem.acervo.model.auxiliar.Cantor;
import ma.cultura.emem.acervo.model.auxiliar.Compositor;
import ma.cultura.emem.acervo.model.auxiliar.Gravadora;
import ma.cultura.emem.acervo.model.auxiliar.Midia;
import ma.cultura.emem.acervo.util.jsf.FacesUtil;

@Named("cdBean")
@ViewScoped
public class CDBean extends ItemAcervoBean<CD> {
	
	private static final long serialVersionUID = 6482945063096362016L;
	
	private Musica musicaAdd = new Musica();
	
	@Override
	public void setEntity(CD itemAcervo) {
		super.setEntity(itemAcervo);
		musicaAdd.setFaixa(getProximaFaixa());
	}
	
	private int getProximaFaixa() {
		int proximaFaixa = 1;
		logger.debug(getCD().getMusicas().size());
		for (Musica m : getCD().getMusicas())
			if (m.getFaixa() >= proximaFaixa)
				proximaFaixa = m.getFaixa() + 1;
		return proximaFaixa;
	}
	
	public void adicionarMusica() {
		getCD().addMusica(musicaAdd);
		musicaAdd = new Musica();
		FacesUtil.hideDialog("dlgMusica");
	}
	
	public void removerMusica(int index) {
		logger.debug("REMOVENDO musica index: " + index);
		if (!this.getCD().getMusicas().isEmpty()) {
			Musica m = this.getCD().getMusicas().remove(index);
			m.setCd(null);
			logger.debug("REMOVIDO DA LISTA: " + m.getTitulo());
		}
	}
	
	public Musica getMusicaAdd() {
		musicaAdd.setFaixa(getProximaFaixa());
		return musicaAdd;
	}
	
	public void setMusicaAdd(Musica musicaAdd) {
		this.musicaAdd = musicaAdd;
	}
	
	// Dialogs / Autocompletes...............................
	public void setCompositor(Compositor c) {
		musicaAdd.getCompositores().add(c);
	}
	
	public void setCantor(Cantor c) {
		getCD().getCantores().add(c);
	}
	
	public void setGravadora(Gravadora g) {
		getCD().setGravadora(g);
	}
	
	// .................................................................
	@Override
	public CD getNewItemAcervo() {
		return new CD();
	}
	
	public CD getCD() {
		return getEntity();
	}
	
	@Override
	public String recarregarPagina() {
		return "cd?faces-redirect=true";
	}
	
	// .................................................................
	
	public List<Midia> getlistaMidias() {
		return itemAcervoService.findAllMidias();
	}
	
	public List<Gravadora> findGravadoras(String nome) {
		return itemAcervoService.findGravadoras(nome);
	}
	
	public List<Cantor> findCantores(String nome) {
		return itemAcervoService.findCantores(nome);
	}
	
	public List<Compositor> findCompositores(String nome) {
		return itemAcervoService.findCompositores(nome);
	}
}