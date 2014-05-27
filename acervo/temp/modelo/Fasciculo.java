package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fasciculo database table.
 * 
 */
@Entity
@NamedQuery(name="Fasciculo.findAll", query="SELECT f FROM Fasciculo f")
public class Fasciculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="obra_id")
	private int obraId;

	private String ehAssinado;

	@Column(name="obra_id1")
	private int obraId1;

	private String tituloFasciculo;

	//bi-directional many-to-one association to Artigo
	@OneToMany(mappedBy="fasciculo")
	private List<Artigo> artigos;

	//bi-directional many-to-one association to Exemplar
	@OneToMany(mappedBy="fasciculo")
	private List<Exemplar> exemplars;

	//bi-directional many-to-one association to Periodico
	@ManyToOne
	private Periodico periodico;

	public Fasciculo() {
	}

	public int getObraId() {
		return this.obraId;
	}

	public void setObraId(int obraId) {
		this.obraId = obraId;
	}

	public String getEhAssinado() {
		return this.ehAssinado;
	}

	public void setEhAssinado(String ehAssinado) {
		this.ehAssinado = ehAssinado;
	}

	public int getObraId1() {
		return this.obraId1;
	}

	public void setObraId1(int obraId1) {
		this.obraId1 = obraId1;
	}

	public String getTituloFasciculo() {
		return this.tituloFasciculo;
	}

	public void setTituloFasciculo(String tituloFasciculo) {
		this.tituloFasciculo = tituloFasciculo;
	}

	public List<Artigo> getArtigos() {
		return this.artigos;
	}

	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}

	public Artigo addArtigo(Artigo artigo) {
		getArtigos().add(artigo);
		artigo.setFasciculo(this);

		return artigo;
	}

	public Artigo removeArtigo(Artigo artigo) {
		getArtigos().remove(artigo);
		artigo.setFasciculo(null);

		return artigo;
	}

	public List<Exemplar> getExemplars() {
		return this.exemplars;
	}

	public void setExemplars(List<Exemplar> exemplars) {
		this.exemplars = exemplars;
	}

	public Exemplar addExemplar(Exemplar exemplar) {
		getExemplars().add(exemplar);
		exemplar.setFasciculo(this);

		return exemplar;
	}

	public Exemplar removeExemplar(Exemplar exemplar) {
		getExemplars().remove(exemplar);
		exemplar.setFasciculo(null);

		return exemplar;
	}

	public Periodico getPeriodico() {
		return this.periodico;
	}

	public void setPeriodico(Periodico periodico) {
		this.periodico = periodico;
	}

}