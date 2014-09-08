package ma.cultura.emem.acervo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import ma.cultura.emem.acervo.model.auxiliar.Periodico;
import ma.cultura.emem.acervo.model.auxiliar.enums.MesEnum;

@Entity
public class Fasciculo extends ItemAcervo {
	
	private static final long serialVersionUID = 7014427257484292347L;
	
	private short volume;
	
	private short numPaginas;
	
	@Enumerated(EnumType.ORDINAL)
	private MesEnum mes;
	
	@ManyToOne
	private Periodico periodico;
	
	@OneToMany(mappedBy = "fasciculo", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Artigo> artigos = new ArrayList<>();
	
	//FIXME remover
	@Transient
	public String getArtigosToString() {
		final StringBuilder builder = new StringBuilder();
		if (getArtigos() != null && !getArtigos().isEmpty()) {
			final Iterator<Artigo> it = getArtigos().iterator();
			while (it.hasNext()) {
				final Artigo a = it.next();
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
	
	public short getNumPaginas() {
		return numPaginas;
	}
	
	public void setNumPaginas(short numPaginas) {
		this.numPaginas = numPaginas;
	}
}
