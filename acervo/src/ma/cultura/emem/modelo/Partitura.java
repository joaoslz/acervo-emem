package ma.cultura.emem.modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ma.cultura.emem.modelo.auxiliar.Genero;
import ma.cultura.emem.modelo.auxiliar.Instrumento;

@Entity
@NamedQueries({ @NamedQuery(name = "Partitura.findAll", query = "from Partitura p order by p.id desc") })
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