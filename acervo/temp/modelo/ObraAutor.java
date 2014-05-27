package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the obra_autor database table.
 * 
 */
@Entity
@Table(name="obra_autor")
@NamedQuery(name="ObraAutor.findAll", query="SELECT o FROM ObraAutor o")
public class ObraAutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ObraAutorPK id;

	private int obra_id;

	//bi-directional many-to-one association to Autor
	@ManyToOne
	@JoinColumn(name="autores_id")
	private Autor autor;

	//bi-directional many-to-one association to Obra
	@ManyToOne
	private Obra obra;

	public ObraAutor() {
	}

	public ObraAutorPK getId() {
		return this.id;
	}

	public void setId(ObraAutorPK id) {
		this.id = id;
	}

	public int getObra_id() {
		return this.obra_id;
	}

	public void setObra_id(int obra_id) {
		this.obra_id = obra_id;
	}

	public Autor getAutor() {
		return this.autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Obra getObra() {
		return this.obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

}