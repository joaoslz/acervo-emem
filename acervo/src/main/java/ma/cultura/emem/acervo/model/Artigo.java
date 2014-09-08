package ma.cultura.emem.acervo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import ma.cultura.emem.acervo.model.auxiliar.Autor;

@Entity
public class Artigo extends RootEntity {
	private static final long serialVersionUID = -7873476524199816664L;
	// @NotNull(message="O título é um campo obrigatório")
	// @Size(min=2, max=100,
	// message="O título deve possuir no máximo 100 caracteres")
	@Column(length = 100, nullable = false)
	private String titulo;
	private String assunto;
	
	private short paginaInicial;
	private short paginaFinal;
	
	@ManyToOne
	private Fasciculo fasciculo;
	
	@ManyToMany
	private List<Autor> autores;
	
	// FIXME pensar em uma refatorar onde tiver esses ..ToString, pois estão
	// poluindo o código.
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
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAssunto() {
		return assunto;
	}
	
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public short getPaginaInicial() {
		return paginaInicial;
	}
	
	public void setPaginaInicial(short paginaInicial) {
		this.paginaInicial = paginaInicial;
	}
	
	public short getPaginaFinal() {
		return paginaFinal;
	}
	
	public void setPaginaFinal(short paginaFinal) {
		this.paginaFinal = paginaFinal;
	}
	
	public List<Autor> getAutores() {
		return autores;
	}
	
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public void addAutor(Autor autor) {
		if (autores == null) {
			autores = new ArrayList<Autor>();
		}
		autores.add(autor);
	}
	
	public Fasciculo getFasciculo() {
		return fasciculo;
	}
	
	public void setFasciculo(Fasciculo fasciculo) {
		this.fasciculo = fasciculo;
	}
}