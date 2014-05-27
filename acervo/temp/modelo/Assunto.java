package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the assunto database table.
 * 
 */
@Entity
@NamedQuery(name="Assunto.findAll", query="SELECT a FROM Assunto a")
public class Assunto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String assunto;

	//bi-directional many-to-many association to ItemAcervo
	@ManyToMany
	@JoinTable(
		name="itemacervo_assunto"
		, joinColumns={
			@JoinColumn(name="assunto_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="item_acervo_id")
			}
		)
	private List<ItemAcervo> itemAcervos;

	public Assunto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssunto() {
		return this.assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public List<ItemAcervo> getItemAcervos() {
		return this.itemAcervos;
	}

	public void setItemAcervos(List<ItemAcervo> itemAcervos) {
		this.itemAcervos = itemAcervos;
	}

}