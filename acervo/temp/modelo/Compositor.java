package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the compositor database table.
 * 
 */
@Entity
@NamedQuery(name="Compositor.findAll", query="SELECT c FROM Compositor c")
public class Compositor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nome;

	//bi-directional many-to-many association to Musica
	@ManyToMany
	@JoinTable(
		name="compositor_musica"
		, joinColumns={
			@JoinColumn(name="compositor_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="musica_CD_id", referencedColumnName="CD_id"),
			@JoinColumn(name="musica_faixa", referencedColumnName="faixa")
			}
		)
	private List<Musica> musicas;

	public Compositor() {
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

	public List<Musica> getMusicas() {
		return this.musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

}