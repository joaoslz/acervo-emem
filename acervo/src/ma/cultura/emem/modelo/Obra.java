package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ma.cultura.emem.modelo.auxiliar.Autor;
import ma.cultura.emem.modelo.auxiliar.TipoObra;

@Entity
public class Obra extends ItemAcervo {

	private static final long serialVersionUID = 8672695121158033015L;

	private String classificacao;

//	@NotNull
//	@Size(min=5, max=5, message="O cutter deve possuir 5 caracteres")
	@Column(length = 5, nullable = false)
	private String cutter;

	private Short edicao;
	private boolean ehIlustrado;


//	@Size(max=20, message="O ISBN não pode ser superior a 20 caracteres")
	@Column(length = 20)
	private String isbn;
	private Short numPaginas;
	private String serie;
	private Short volume;
	@Transient
	private boolean naoPaginado = false;

	@ManyToOne
	private TipoObra tipoObra;

	@ManyToMany
	private List<Autor> autores = new ArrayList<>();

	public void setNaoPaginado(boolean naoPaginado) {
		if (naoPaginado)
			numPaginas = 0;
		this.naoPaginado = naoPaginado;
	}

	public boolean getNaoPaginado() {
		return numPaginas == null && naoPaginado;
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

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getCutter() {
		return cutter;
	}

	public void setCutter(String cutter) {
		this.cutter = cutter;
	}

	public Short getEdicao() {
		return edicao;
	}

	public void setEdicao(Short edicao) {
		this.edicao = edicao;
	}

	public boolean isEhIlustrado() {
		return ehIlustrado;
	}

	public void setEhIlustrado(boolean ehIlustrado) {
		this.ehIlustrado = ehIlustrado;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Short getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(Short numPaginas) {
		this.numPaginas = numPaginas;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Short getVolume() {
		return volume;
	}

	public void setVolume(Short volume) {
		this.volume = volume;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public void addAutor(Autor autor) {
		if (autores == null)
			autores = new ArrayList<Autor>();
		autores.add(autor);
	}

	public TipoObra getTipoObra() {
		return tipoObra;
	}

	public void setTipoObra(TipoObra tipoObra) {
		this.tipoObra = tipoObra;
	}
}