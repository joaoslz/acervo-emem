package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the editora database table.
 * 
 */
@Entity
@NamedQuery(name="Editora.findAll", query="SELECT e FROM Editora e")
public class Editora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nome;

	private String site;

	//bi-directional many-to-one association to ItemAcervo
	@OneToMany(mappedBy="editora")
	private List<ItemAcervo> itemAcervos;

	public Editora() {
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

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public List<ItemAcervo> getItemAcervos() {
		return this.itemAcervos;
	}

	public void setItemAcervos(List<ItemAcervo> itemAcervos) {
		this.itemAcervos = itemAcervos;
	}

	public ItemAcervo addItemAcervo(ItemAcervo itemAcervo) {
		getItemAcervos().add(itemAcervo);
		itemAcervo.setEditora(this);

		return itemAcervo;
	}

	public ItemAcervo removeItemAcervo(ItemAcervo itemAcervo) {
		getItemAcervos().remove(itemAcervo);
		itemAcervo.setEditora(null);

		return itemAcervo;
	}

}