package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the artigo database table.
 * 
 */
@Entity
@NamedQuery(name="Artigo.findAll", query="SELECT a FROM Artigo a")
public class Artigo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String assunto;

	private short paginaFinal;

	private short paginaInicial;

	@Column(name="periodico_id")
	private int periodicoId;

	private String titulo;

	//bi-directional many-to-one association to Fasciculo
	@ManyToOne
	private Fasciculo fasciculo;

	//bi-directional many-to-many association to Autor
	@ManyToMany
	@JoinTable(
		name="artigo_autor"
		, joinColumns={
			@JoinColumn(name="Artigo_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="autores_id")
			}
		)
	private List<Autor> autors;

	public Artigo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssunto() {
		return this.assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public short getPaginaFinal() {
		return this.paginaFinal;
	}

	public void setPaginaFinal(short paginaFinal) {
		this.paginaFinal = paginaFinal;
	}

	public short getPaginaInicial() {
		return this.paginaInicial;
	}

	public void setPaginaInicial(short paginaInicial) {
		this.paginaInicial = paginaInicial;
	}

	public int getPeriodicoId() {
		return this.periodicoId;
	}

	public void setPeriodicoId(int periodicoId) {
		this.periodicoId = periodicoId;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Fasciculo getFasciculo() {
		return this.fasciculo;
	}

	public void setFasciculo(Fasciculo fasciculo) {
		this.fasciculo = fasciculo;
	}

	public List<Autor> getAutors() {
		return this.autors;
	}

	public void setAutors(List<Autor> autors) {
		this.autors = autors;
	}

}