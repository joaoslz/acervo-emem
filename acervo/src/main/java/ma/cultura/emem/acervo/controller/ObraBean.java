package ma.cultura.emem.acervo.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.Obra;
import ma.cultura.emem.acervo.model.auxiliar.Autor;

@Named
@ViewScoped
public class ObraBean extends ItemAcervoBean<Obra> {
	
	private static final long serialVersionUID = 3468148839276090985L;
	
	public void setAutor(Autor a) {
		getObra().addAutor(a);
		logger.debug("Adicionando Autor na Obra: " + a);
	}
	
	@Override
	public Obra getNewItemAcervo() {
		return new Obra();
	}
	
	@Override
	public String recarregarPagina() {
		return "obra?faces-redirect=true";
	}
	
	public Obra getObra() {
		return getEntity();
	}
}