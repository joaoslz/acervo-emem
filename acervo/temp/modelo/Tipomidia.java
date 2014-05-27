package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipomidia database table.
 * 
 */
@Entity
@NamedQuery(name="Tipomidia.findAll", query="SELECT t FROM Tipomidia t")
public class Tipomidia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nome;

	//bi-directional many-to-one association to Cd
	@OneToMany(mappedBy="tipomidia")
	private List<Cd> cds;

	//bi-directional many-to-one association to Midia
	@OneToMany(mappedBy="tipomidia")
	private List<Midia> midias;

	public Tipomidia() {
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
		cd.setTipomidia(this);

		return cd;
	}

	public Cd removeCd(Cd cd) {
		getCds().remove(cd);
		cd.setTipomidia(null);

		return cd;
	}

	public List<Midia> getMidias() {
		return this.midias;
	}

	public void setMidias(List<Midia> midias) {
		this.midias = midias;
	}

	public Midia addMidia(Midia midia) {
		getMidias().add(midia);
		midia.setTipomidia(this);

		return midia;
	}

	public Midia removeMidia(Midia midia) {
		getMidias().remove(midia);
		midia.setTipomidia(null);

		return midia;
	}

}