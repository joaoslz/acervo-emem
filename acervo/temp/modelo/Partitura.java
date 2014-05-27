package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the partitura database table.
 * 
 */
@Entity
@NamedQuery(name="Partitura.findAll", query="SELECT p FROM Partitura p")
public class Partitura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_acervo_id")
	private int itemAcervoId;

	//bi-directional one-to-one association to ItemAcervo
	@OneToOne
	@JoinColumn(name="item_acervo_id")
	private ItemAcervo itemAcervo;

	//bi-directional many-to-one association to Genero
	@ManyToOne
	private Genero genero;

	//bi-directional many-to-one association to Instrumento
	@ManyToOne
	private Instrumento instrumento;

	public Partitura() {
	}

	public int getItemAcervoId() {
		return this.itemAcervoId;
	}

	public void setItemAcervoId(int itemAcervoId) {
		this.itemAcervoId = itemAcervoId;
	}

	public ItemAcervo getItemAcervo() {
		return this.itemAcervo;
	}

	public void setItemAcervo(ItemAcervo itemAcervo) {
		this.itemAcervo = itemAcervo;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Instrumento getInstrumento() {
		return this.instrumento;
	}

	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}

}