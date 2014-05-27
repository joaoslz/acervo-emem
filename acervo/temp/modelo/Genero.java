package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the genero database table.
 * 
 */
@Entity
@NamedQuery(name="Genero.findAll", query="SELECT g FROM Genero g")
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nome;

	//bi-directional many-to-one association to Partitura
	@OneToMany(mappedBy="genero")
	private List<Partitura> partituras;

	public Genero() {
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

	public List<Partitura> getPartituras() {
		return this.partituras;
	}

	public void setPartituras(List<Partitura> partituras) {
		this.partituras = partituras;
	}

	public Partitura addPartitura(Partitura partitura) {
		getPartituras().add(partitura);
		partitura.setGenero(this);

		return partitura;
	}

	public Partitura removePartitura(Partitura partitura) {
		getPartituras().remove(partitura);
		partitura.setGenero(null);

		return partitura;
	}

}