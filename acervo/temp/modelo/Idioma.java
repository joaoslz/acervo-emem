package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the idioma database table.
 * 
 */
@Entity
@NamedQuery(name="Idioma.findAll", query="SELECT i FROM Idioma i")
public class Idioma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nome;

	//bi-directional many-to-one association to Cd
	@OneToMany(mappedBy="idioma")
	private List<Cd> cds;

	//bi-directional many-to-one association to ItemAcervo
	@OneToMany(mappedBy="idioma")
	private List<ItemAcervo> itemAcervos;

	public Idioma() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cd> getCds() {
		return this.cds;
	}

	public void setCds(List<Cd> cds) {
		this.cds = cds;
	}

	public Cd addCd(Cd cd) {
		getCds().add(cd);
		cd.setIdioma(this);

		return cd;
	}

	public Cd removeCd(Cd cd) {
		getCds().remove(cd);
		cd.setIdioma(null);

		return cd;
	}

	public List<ItemAcervo> getItemAcervos() {
		return this.itemAcervos;
	}

	public void setItemAcervos(List<ItemAcervo> itemAcervos) {
		this.itemAcervos = itemAcervos;
	}

	public ItemAcervo addItemAcervo(ItemAcervo itemAcervo) {
		getItemAcervos().add(itemAcervo);
		itemAcervo.setIdioma(this);

		return itemAcervo;
	}

	public ItemAcervo removeItemAcervo(ItemAcervo itemAcervo) {
		getItemAcervos().remove(itemAcervo);
		itemAcervo.setIdioma(null);

		return itemAcervo;
	}

}