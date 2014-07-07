package ma.cultura.emem.modelo;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

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
public abstract class ItemAcervo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotEmpty(message="O título é um campo obrigatório")
    @Size(max=100, message="O título deve possuir no máximo 100 caracteres!")
	@Column(length = 100, nullable = false)
	private String titulo;

    @Size(max=100, message="O subtitulo deve possuir no máximo 100 caracteres!")
	@Column(length = 100)
	private String subtitulo;

//    @Min(1500)
	private short ano;

	@ManyToOne
	private Editora editora;

	@ManyToOne
	private Local local;

	@ManyToOne
	private Idioma idioma;

	@ManyToMany
	private List<Assunto> assuntos = new ArrayList<Assunto>();

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
		final StringBuilder builder = new StringBuilder();
		if (getAssuntos() != null) {
			final Iterator<Assunto> it = getAssuntos().iterator();
			while (it.hasNext()) {
				final Assunto a = it.next();
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