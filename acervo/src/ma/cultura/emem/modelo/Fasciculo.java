package ma.cultura.emem.modelo;

import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import ma.cultura.emem.modelo.auxiliar.MesEnum;

@Entity
public class Fasciculo extends BaseEntity {

	private static final long serialVersionUID = -1467686486369558017L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String titulo;
	private String subtitulo;
	private short edicao;
	@Enumerated(EnumType.ORDINAL)
	private MesEnum mes;
	private short ano;

	@ManyToOne
	private Periodico periodico;

	@OneToMany(mappedBy = "fasciculo")
	private List<Exemplar> exemplares;// XXX = new ArrayList<>();

	@OneToMany(mappedBy = "fasciculo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Artigo> artigos;// XXX = new ArrayList<>();



	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
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
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtituslo) {
		this.subtitulo = subtituslo;
	}

	public short getEdicao() {
		return edicao;
	}

	public void setEdicao(short edicao) {
		this.edicao = edicao;
	}

	public List<Exemplar> getExemplares() {
		return exemplares;
	}

	public void setAno(short ano) {
		this.ano = ano;
	}

	public Short getAno() {
		return ano;
	}

	public void setAno(Short ano) {
		this.ano = ano;
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
}
