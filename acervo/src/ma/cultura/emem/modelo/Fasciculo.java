package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import ma.cultura.emem.modelo.auxiliar.MesEnum;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Fasciculo.findAll", query = "from Fasciculo f order by f.id desc"),
	@NamedQuery(name = "Fasciculo.findByPeriodico", query = "from Fasciculo f WHERE f.periodico.id = :idPeriodico order by f.ano desc, f.mes desc")
})
public class Fasciculo extends ItemAcervo {

	private static final long serialVersionUID = -1467686486369558017L;
	
	private short volume;
	
	private short numPaginas;
	
	@Enumerated(EnumType.ORDINAL)
	private MesEnum mes;

	@ManyToOne
	private Periodico periodico;

	@OneToMany(mappedBy = "fasciculo")
	private List<Exemplar> exemplares = new ArrayList<>();

	@OneToMany(mappedBy = "fasciculo")
	private List<Artigo> artigos = new ArrayList<>();

	@Transient
	public String getArtigosToString() {
		StringBuilder builder = new StringBuilder();
		if (getArtigos() != null && !getArtigos().isEmpty()) {
			Iterator<Artigo> it = getArtigos().iterator();
			while (it.hasNext()) {
				Artigo a = it.next();
				builder.append(a.getTitulo());
				if (it.hasNext()) {
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}
	
	public short getVolume() {
		return volume;
	}

	public void setVolume(short edicao) {
		this.volume = edicao;
	}

	public List<Exemplar> getExemplares() {
		return exemplares;
	}

	public MesEnum getMes() {
		return mes;
	}

	public void setMes(MesEnum m) {
		this.mes = m;
	}

	public Periodico getPeriodico() {
		return periodico;
	}

	public void setPeriodico(Periodico periodico) {
		this.periodico = periodico;
	}

	public List<Artigo> getArtigos() {
		return artigos;
	}

	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}

	public void addArtigo(Artigo artigo) {
		artigos.add(artigo);
		artigo.setFasciculo(this);
	}

	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}

	public short getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(short numPaginas) {
		this.numPaginas = numPaginas;
	}
}
