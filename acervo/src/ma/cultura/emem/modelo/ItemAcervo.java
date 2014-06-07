package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ma.cultura.emem.modelo.auxiliar.Assunto;
import ma.cultura.emem.modelo.auxiliar.Editora;
import ma.cultura.emem.modelo.auxiliar.Idioma;
import ma.cultura.emem.modelo.auxiliar.Local;

/**
 * The persistent class for the item_acervo database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "item_acervo")
@NamedQueries({
	@NamedQuery(name = "ItemAcervo.findAll", query = "SELECT i FROM ItemAcervo i"),
	@NamedQuery(name = "ItemAcervo.findByTitulo", query = "from Obra a where a.titulo like :titulo")
})
public abstract class ItemAcervo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=100, nullable=false)
	private String titulo;
	
	@Column(length=100)
	private String subtitulo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro;// XXX = Calendar.getInstance();

	private short ano;
	
	@ManyToOne
	private Editora editora;
	
	@ManyToOne
	private Local local;
	
	@ManyToOne
	private Idioma idioma;
	
	@ManyToMany
	private List<Assunto> assuntos = new ArrayList<Assunto>();;
	
	@OneToMany(mappedBy = "itemAcervo")
	private List<Exemplar> exemplares = new ArrayList<Exemplar>();;

	public ItemAcervo() {
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void addExemplar(Exemplar exemplar) {
		if (exemplares == null)
			exemplares = new ArrayList<Exemplar>();
		exemplares.add(exemplar);
		exemplar.setItemAcervo(this);
	}

	public void addAssunto(Assunto assunto) {
		if (assuntos == null)
			assuntos = new ArrayList<Assunto>();
		assuntos.add(assunto);
	}

	@Transient
	public String getAssuntosToString() {
		StringBuilder builder = new StringBuilder();
		if (getAssuntos() != null) {
			Iterator<Assunto> it = getAssuntos().iterator();
			while (it.hasNext()) {
				Assunto a = it.next();
				builder.append(a.getNome());
				if (it.hasNext()) {
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}

	@Override
	public String toString() {
		return titulo;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
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

	public short getAno() {
		return ano;
	}

	public void setAno(short ano) {
		this.ano = ano;
	}
}