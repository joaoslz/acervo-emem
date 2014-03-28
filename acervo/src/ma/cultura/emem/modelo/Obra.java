package ma.cultura.emem.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Obra implements Serializable {

	private static final long serialVersionUID = 3049612542392248546L;

	@Id
	@GeneratedValue
	private Integer id;

	private String titulo;
	private String subtitulo;
	private short ano;
	private short numPaginas;
	private short edicao;
	private boolean ehIlustrado;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro = Calendar.getInstance();

	@ManyToOne
	private Idioma idioma;

	@ManyToMany
	private List<Autor> autores;

	// FIXME fetch eager???? não consegui fazer via HQL pq ja tem fetch pro
	// autor.
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Assunto> assuntos;

	@OneToMany(mappedBy = "obra", cascade = CascadeType.PERSIST)
	private List<Exemplar> exemplares;

	@ManyToOne
	private Editora editora;

	@ManyToOne
	private Local local;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public short getAno() {
		return ano;
	}

	public void setAno(short ano) {
		this.ano = ano;
	}

	public short getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(short numPaginas) {
		this.numPaginas = numPaginas;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public List<Exemplar> getExemplares() {
		return exemplares;
	}

	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public void adicionarAutor(Autor autor) {
		if (autores == null)
			autores = new ArrayList<Autor>();
		autores.add(autor);
	}

	public boolean isEhIlustrado() {
		return ehIlustrado;
	}

	public void setEhIlustrado(boolean ehIlustrado) {
		this.ehIlustrado = ehIlustrado;
	}

	public void adicionarExemplar(Exemplar exemplar) {
		if (exemplares == null)
			exemplares = new ArrayList<Exemplar>();
		exemplares.add(exemplar);
	}

	public short getEdicao() {
		return edicao;
	}

	public void setEdicao(short edicao) {
		this.edicao = edicao;
	}
}