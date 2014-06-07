package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import ma.cultura.emem.modelo.auxiliar.Autor;
import ma.cultura.emem.modelo.auxiliar.TipoObra;

@Entity
@NamedQueries({
		@NamedQuery(name = "Obra.findAll", query = "from Obra a order by a.id desc")
})
public class Obra extends ItemAcervo {

	private static final long serialVersionUID = 8672695121158033015L;

	private String classificacao;

	@Column(length=6, nullable=false)
	private String cutter;
	
	private short edicao;
	private boolean ehIlustrado;
	
	@Column(length=20)
	private String isbn;
	private short numPaginas;
	private String serie;
	private short volume;
	@Transient
	private boolean naoPaginado = false;
	
	@ManyToOne
	private TipoObra tipoObra;

	@ManyToMany
	private List<Autor> autores;

	public void setNaoPaginado(boolean naoPaginado) {
		if(naoPaginado)
			numPaginas = 0;
		this.naoPaginado = naoPaginado;
	}

	public boolean getNaoPaginado() {
		return numPaginas <= 0 && naoPaginado;
	}

	@Transient
	public String getAutoresToString() {
		StringBuilder builder = new StringBuilder();
		if (getAutores() != null) {
			Iterator<Autor> it = getAutores().iterator();
			while (it.hasNext()) {
				Autor a = it.next();
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

	public short getEdicao() {
		return edicao;
	}

	public void setEdicao(short edicao) {
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

	public short getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(short numPaginas) {
		this.numPaginas = numPaginas;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public short getVolume() {
		return volume;
	}

	public void setVolume(short volume) {
		this.volume = volume;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public void addAutor(Autor autor){
		if(autores == null)
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