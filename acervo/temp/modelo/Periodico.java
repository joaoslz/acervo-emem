package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the periodico database table.
 * 
 */
@Entity
@NamedQuery(name="Periodico.findAll", query="SELECT p FROM Periodico p")
public class Periodico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_acervo_id")
	private int itemAcervoId;

	private byte ehAssinado;

	private String issn;

	//bi-directional many-to-one association to Fasciculo
	@OneToMany(mappedBy="periodico")
	private List<Fasciculo> fasciculos;

	//bi-directional one-to-one association to ItemAcervo
	@OneToOne
	@JoinColumn(name="item_acervo_id")
	private ItemAcervo itemAcervo;

	public Periodico() {
	}

	public int getItemAcervoId() {
		return this.itemAcervoId;
	}

	public void setItemAcervoId(int itemAcervoId) {
		this.itemAcervoId = itemAcervoId;
	}

	public byte getEhAssinado() {
		return this.ehAssinado;
	}

	public void setEhAssinado(byte ehAssinado) {
		this.ehAssinado = ehAssinado;
	}

	public String getIssn() {
		return this.issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public List<Fasciculo> getFasciculos() {
		return this.fasciculos;
	}

	public void setFasciculos(List<Fasciculo> fasciculos) {
		this.fasciculos = fasciculos;
	}

	public Fasciculo addFasciculo(Fasciculo fasciculo) {
		getFasciculos().add(fasciculo);
		fasciculo.setPeriodico(this);

		return fasciculo;
	}

	public Fasciculo removeFasciculo(Fasciculo fasciculo) {
		getFasciculos().remove(fasciculo);
		fasciculo.setPeriodico(null);

		return fasciculo;
	}

	public ItemAcervo getItemAcervo() {
		return this.itemAcervo;
	}

	public void setItemAcervo(ItemAcervo itemAcervo) {
		this.itemAcervo = itemAcervo;
	}

}