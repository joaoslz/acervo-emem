package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the musica database table.
 * 
 */
@Entity
@NamedQuery(name="Musica.findAll", query="SELECT m FROM Musica m")
public class Musica implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MusicaPK id;

	private Time duracao;

	private String titulo;

	//bi-directional many-to-many association to Compositor
	@ManyToMany(mappedBy="musicas")
	private List<Compositor> compositors;

	//bi-directional many-to-one association to Midia
	@ManyToOne
	private Midia midia;

	public Musica() {
	}

	public MusicaPK getId() {
		return this.id;
	}

	public void setId(MusicaPK id) {
		this.id = id;
	}

	public Time getDuracao() {
		return this.duracao;
	}

	public void setDuracao(Time duracao) {
		this.duracao = duracao;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Compositor> getCompositors() {
		return this.compositors;
	}

	public void setCompositors(List<Compositor> compositors) {
		this.compositors = compositors;
	}

	public Midia getMidia() {
		return this.midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

}