package ma.cultura.emem.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
	@NamedQuery(name = Obra.NAMED_QUERY_LISTAR_TODOS, query = "from Obra a order by a.id desc"),
	@NamedQuery(name = Obra.NAMED_QUERY_PESQUISAR_POR_TITULO, query = "from Obra a where a.titulo like :titulo"),
	@NamedQuery(name = Obra.NAMED_QUERY_PESQUISAR_POR_AUTOR, query = "select distinct a from Obra a left join fetch a.autores t where t.nome like :autor"),
	@NamedQuery(name = Obra.NAMED_QUERY_PESQUISAR_POR_ASSUNTO, query = "select distinct a from Obra a left join fetch a.assuntos s where s.assunto like :assunto"),
	@NamedQuery(name = Obra.NAMED_QUERY_PESQUISAR_POR_EDITORA, query = "from Obra a where a.editora.nome like :editora")

})
public abstract class Obra implements Serializable {

	public static final String NAMED_QUERY_LISTAR_TODOS = "Obra.listarTodos";
	public static final String NAMED_QUERY_PESQUISAR_POR_TITULO = "Obra.pesquisarPorTitulo";
	public static final String NAMED_QUERY_PESQUISAR_POR_AUTOR = "Obra.pesquisarPorAutor";
	public static final String NAMED_QUERY_PESQUISAR_POR_ASSUNTO = "Obra.pesquisarPorAssunto";
	public static final String NAMED_QUERY_PESQUISAR_POR_EDITORA = "Obra.pesquisarPorEditora";

	private static final long serialVersionUID = 8672695121158033015L;

	@Id
	@GeneratedValue
	private Integer id;

	private String titulo;
	private String subtitulo;
	private Short ano;
	private Short numPaginas;
	private Short edicao;
	private boolean ehIlustrado;
	private Short volume;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro = Calendar.getInstance();

	@ManyToOne
	private Idioma idioma;

	@ManyToMany
	private List<Autor> autores;

	// FIXME fetch eager???? não consegui fazer via HQL pq ja tem fetch pro
	// autor.
	@ManyToMany
	private List<Assunto> assuntos;

	@OneToMany(mappedBy = "obra")
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

	public Short getAno() {
		return ano;
	}

	public void setAno(Short ano) {
		this.ano = ano;
	}

	public Short getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(Short numPaginas) {
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
	
	public void adicionarAssunto(Assunto assunto){
	    if(assuntos == null)
		assuntos = new ArrayList<Assunto>();
	    assuntos.add(assunto);
	}

	public Short getEdicao() {
		return edicao;
	}

	public void setEdicao(Short edicao) {
		this.edicao = edicao;
	}
	
	public boolean isIdNull(){
	    return id == null || id.intValue() == 0;
	}

	public Short getVolume() {
	return volume;
	}

	public void setVolume(Short volume) {
	this.volume = volume;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	public String getAutoresToString(){
		StringBuilder builder = new StringBuilder();
		if(getAutores() != null){
			Iterator<Autor> it = getAutores().iterator();
			while(it.hasNext()){
				Autor a = it.next();
				builder.append(a.getNome());
				if(it.hasNext()){
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}

	public String getAssuntosToString(){
		StringBuilder builder = new StringBuilder();
		if(getAssuntos() != null){
			Iterator<Assunto> it = getAssuntos().iterator();
			while(it.hasNext()){
				Assunto a = it.next();
				builder.append(a.getAssunto());
				if(it.hasNext()){
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}
}