package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the instrumento database table.
 * 
 */
@Entity
@NamedQuery(name="Instrumento.findAll", query="SELECT i FROM Instrumento i")
public class Instrumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nome;

	//bi-directional many-to-one association to Partitura
	@OneToMany(mappedBy="instrumento")
	private List<Partitura> partituras;

	public Instrumento() {
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
		partitura.setInstrumento(this);

		return partitura;
	}

	public Partitura removePartitura(Partitura partitura) {
		getPartituras().remove(partitura);
		partitura.setInstrumento(null);

		return partitura;
	}

}