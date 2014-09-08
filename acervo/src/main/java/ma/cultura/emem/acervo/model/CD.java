package ma.cultura.emem.acervo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ma.cultura.emem.acervo.model.auxiliar.Cantor;
import ma.cultura.emem.acervo.model.auxiliar.Gravadora;
import ma.cultura.emem.acervo.model.auxiliar.Midia;

@Entity
public class CD extends ItemAcervo {
	private static final long serialVersionUID = -8893371864066224648L;
	
	private String comentario;
	
	@ManyToOne
	private Gravadora gravadora;
	
	@ManyToOne
	private Midia midia;
	
	@ManyToMany
	private List<Cantor> cantores = new ArrayList<Cantor>();
	
	@OneToMany(mappedBy = "cd", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Musica> musicas = new ArrayList<>();
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public List<Musica> getMusicas() {
		return musicas;
	}
	
	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}
	
	public Gravadora getGravadora() {
		return gravadora;
	}
	
	public void setGravadora(Gravadora gravadora) {
		this.gravadora = gravadora;
	}
	
	public Midia getMidia() {
		return midia;
	}
	
	public void setMidia(Midia midia) {
		this.midia = midia;
	}
	
	public List<Cantor> getCantores() {
		if (cantores == null) {
			cantores = new ArrayList<>();
		}
		return cantores;
	}
	
	public void setCantores(List<Cantor> cantores) {
		this.cantores = cantores;
	}
	
	public void addMusica(Musica m) {
		m.setCd(this);
		getMusicas().add(m);
	}
	
	public String getMusicasToString() {
		final StringBuilder builder = new StringBuilder();
		if (getCantores() != null) {
			final Iterator<Musica> it = getMusicas().iterator();
			while (it.hasNext()) {
				final Musica m = it.next();
				builder.append(m.toString());
				if (it.hasNext()) {
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}
	
	public String getCantoresToString() {
		final StringBuilder builder = new StringBuilder();
		if (getCantores() != null) {
			final Iterator<Cantor> it = getCantores().iterator();
			while (it.hasNext()) {
				final Cantor c = it.next();
				builder.append(c.getNome());
				if (it.hasNext()) {
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}
}