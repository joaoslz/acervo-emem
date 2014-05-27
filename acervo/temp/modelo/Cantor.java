package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cantor database table.
 * 
 */
@Entity
@NamedQuery(name="Cantor.findAll", query="SELECT c FROM Cantor c")
public class Cantor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nome;

	//bi-directional many-to-many association to Cd
	@ManyToMany
	@JoinTable(
		name="cantor_cd"
		, joinColumns={
			@JoinColumn(name="cantor_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="CD_id")
			}
		)
	private List<Cd> cds;

	public Cantor() {
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

}