package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import ma.cultura.emem.modelo.auxiliar.Editora;
import ma.cultura.emem.modelo.auxiliar.Idioma;
import ma.cultura.emem.modelo.auxiliar.Local;
import ma.cultura.emem.modelo.auxiliar.PeriodicidadeEnum;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Periodico.findAll", query = "from Periodico p order by p.id desc"),
	@NamedQuery(name = "Periodico.findByNome", query = "from Periodico p WHERE p.nome like :nome order by p.nome asc")
})
public class Periodico extends BaseEntity {

	private static final long serialVersionUID = -112232541131788319L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
	@Column(length=20)
	private String issn;
	
	private Boolean ehAssinado;

	@Enumerated(EnumType.STRING)
	private PeriodicidadeEnum periodicidade;

	@ManyToOne
	private Editora editora;
	
	@ManyToOne
	private Local local;
	
	@ManyToOne
	private Idioma idioma;
	
	@OneToMany(mappedBy = "periodico")
	private List<Fasciculo> fasciculos;



	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public Boolean getEhAssinado() {
		return ehAssinado;
	}

	public void setEhAssinado(Boolean ehAssinado) {
		this.ehAssinado = ehAssinado;
	}

	public PeriodicidadeEnum getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(PeriodicidadeEnum periodicidade) {
		this.periodicidade = periodicidade;
	}

	public List<Fasciculo> getFasciculos() {
		return fasciculos;
	}

	public void setFasciculos(List<Fasciculo> fasciculos) {
		this.fasciculos = fasciculos;
	}

	public Fasciculo addFasciculo(Fasciculo fasciculo) {
		if(fasciculos == null)
			fasciculos = new ArrayList<>();
		getFasciculos().add(fasciculo);
		fasciculo.setPeriodico(this);

		return fasciculo;
	}

	public Fasciculo removeFasciculo(Fasciculo fasciculo) {
		getFasciculos().remove(fasciculo);
		fasciculo.setPeriodico(null);

		return fasciculo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
}