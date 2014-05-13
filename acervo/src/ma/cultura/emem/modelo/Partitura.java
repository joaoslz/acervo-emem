package ma.cultura.emem.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue(value = "partitura")
@NamedQueries({ @NamedQuery(name = "Partitura.listarTodos", query = "from Partitura p order by p.id desc") })
public class Partitura extends Obra {

	private static final long serialVersionUID = -3070004622107746166L;

	@ManyToOne
	private Instrumento instrumento;

	@ManyToOne
	private Genero genero;

	/**
	 * @return the instrumento
	 */
	public Instrumento getInstrumento() {
		return instrumento;
	}

	/**
	 * @param instrumento the instrumento to set
	 */
	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
}