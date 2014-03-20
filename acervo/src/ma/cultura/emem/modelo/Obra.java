package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Obra {

	@Id
	@GeneratedValue
	private Integer id;

	private String titulo;
	private String subtitulo;
	private double preco;
	private short ano;

	private short numPaginas;

	@ManyToMany
	private final List<Autor> autores = new ArrayList<Autor>();

	@ManyToMany
	private final List<Assunto> assuntos = new ArrayList<Assunto>();

	@OneToMany(mappedBy = "obra")
	private final List<Exemplar> exemplares = new ArrayList<Exemplar>();

	@ManyToOne
	private Editora editora;

	@Temporal(TemporalType.DATE)
	private final Calendar dataCadastro = Calendar.getInstance();

	public Obra() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public short getAno() {
		return ano;
	}

	public void setAno(short ano) {
		this.ano = ano;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}

	public void adicionaAssunto(Assunto assunto) {
		this.assuntos.add(assunto);
	}

	public short getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(short numPaginas) {
		this.numPaginas = numPaginas;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
}