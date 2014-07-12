package ma.cultura.emem.acervo.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import ma.cultura.emem.acervo.modelo.auxiliar.Autor;
import ma.cultura.emem.acervo.modelo.auxiliar.Genero;
import ma.cultura.emem.acervo.modelo.auxiliar.Instrumento;

@Entity
public class Partitura extends ItemAcervo {

	private static final long serialVersionUID = -3949283338093284387L;

	@ManyToOne
	private Genero genero;

	@ManyToMany
	private List<Instrumento> instrumentos = new ArrayList<>();
	
	@ManyToMany
	private List<Autor> autores = new ArrayList<>();

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Instrumento> getInstrumentos() {
		if (instrumentos == null)
			instrumentos = new ArrayList<>();
		return instrumentos;
	}

	public void setInstrumentos(List<Instrumento> instrumentos) {
		this.instrumentos = instrumentos;
	}

	public List<Autor> getAutores() {
		if (autores == null)
			autores = new ArrayList<>();
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public void addInstrumento(Instrumento i){
		getInstrumentos().add(i);
	}

	public void addAutor(Autor autor) {
		getAutores().add(autor);
	}

	@Transient
	public String getAutoresToString() {
		final StringBuilder builder = new StringBuilder();
		if (getAutores() != null) {
			final Iterator<Autor> it = getAutores().iterator();
			while (it.hasNext()) {
				final Autor a = it.next();
				builder.append(a.getNome());
				if (it.hasNext()) {
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}
	@Transient
	public String getInstrumentosToString() {
		final StringBuilder builder = new StringBuilder();
		if (getInstrumentos() != null) {
			final Iterator<Instrumento> it = getInstrumentos().iterator();
			while (it.hasNext()) {
				final Instrumento a = it.next();
				builder.append(a.getNome());
				if (it.hasNext()) {
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}
}