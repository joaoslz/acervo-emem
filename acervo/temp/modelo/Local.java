package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the local database table.
 * 
 */
@Entity
@NamedQuery(name="Local.findAll", query="SELECT l FROM Local l")
public class Local implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nome;

	//bi-directional many-to-one association to Cd
	@OneToMany(mappedBy="local")
	private List<Cd> cds;

	//bi-directional many-to-one association to ItemAcervo
	@OneToMany(mappedBy="local")
	private List<ItemAcervo> itemAcervos;

	public Local() {
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
		cd.setLocal(this);

		return cd;
	}

	public Cd removeCd(Cd cd) {
		getCds().remove(cd);
		cd.setLocal(null);

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
		itemAcervo.setLocal(this);

		return itemAcervo;
	}

	public ItemAcervo removeItemAcervo(ItemAcervo itemAcervo) {
		getItemAcervos().remove(itemAcervo);
		itemAcervo.setLocal(null);

		return itemAcervo;
	}

}