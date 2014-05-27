package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the autor database table.
 * 
 */
@Entity
@NamedQuery(name="Autor.findAll", query="SELECT a FROM Autor a")
public class Autor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte ehAutorArtigo;

	private String nome;

	//bi-directional many-to-many association to Artigo
	@ManyToMany(mappedBy="autors")
	private List<Artigo> artigos;

	//bi-directional many-to-one association to ObraAutor
	@OneToMany(mappedBy="autor")
	private List<ObraAutor> obraAutors;

	public Autor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getEhAutorArtigo() {
		return this.ehAutorArtigo;
	}

	public void setEhAutorArtigo(byte ehAutorArtigo) {
		this.ehAutorArtigo = ehAutorArtigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Artigo> getArtigos() {
		return this.artigos;
	}

	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}

	public List<ObraAutor> getObraAutors() {
		return this.obraAutors;
	}

	public void setObraAutors(List<ObraAutor> obraAutors) {
		this.obraAutors = obraAutors;
	}

	public ObraAutor addObraAutor(ObraAutor obraAutor) {
		getObraAutors().add(obraAutor);
		obraAutor.setAutor(this);

		return obraAutor;
	}

	public ObraAutor removeObraAutor(ObraAutor obraAutor) {
		getObraAutors().remove(obraAutor);
		obraAutor.setAutor(null);

		return obraAutor;
	}

}