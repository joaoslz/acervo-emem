package ma.cultura.emem.modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ma.cultura.emem.modelo.auxiliar.Genero;
import ma.cultura.emem.modelo.auxiliar.Instrumento;

@Entity
public class Partitura extends ItemAcervo {

	private static final long serialVersionUID = -3070004622107746166L;

	@ManyToOne
	private Instrumento instrumento;
	@ManyToOne
	private Genero genero;

	public Instrumento getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
}