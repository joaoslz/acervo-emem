package ma.cultura.emem.acervo.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ma.cultura.emem.acervo.model.Partitura;
import ma.cultura.emem.acervo.model.auxiliar.Arranjador;
import ma.cultura.emem.acervo.model.auxiliar.Autor;
import ma.cultura.emem.acervo.model.auxiliar.Instrumento;

@Named
@ViewScoped
public class PartituraBean extends ItemAcervoBean<Partitura> implements Serializable {

	private static final long serialVersionUID = -5763773183540321467L;
	
	public void setArranjador(Arranjador a) {
		getPartitura().addArranjador(a);
	}
	
	public void setAutor(Autor a) {
		getPartitura().addAutor(a);
	}

	public void setInstrumento(Instrumento i) {
		getPartitura().addInstrumento(i);
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
		return getEntity();
	}
}