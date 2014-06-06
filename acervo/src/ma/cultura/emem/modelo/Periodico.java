package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import ma.cultura.emem.modelo.auxiliar.PeriodicidadeEnum;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Periodico.findAll", query = "from Periodico p order by p.id desc"),
	@NamedQuery(name = "Periodico.findByTitulo", query = "from Periodico p WHERE p.titulo like :titulo") 
})
public class Periodico extends ItemAcervo {

	private static final long serialVersionUID = -112232541131788319L;

	@Column(length=20)
	private String issn;
	private Boolean ehAssinado;

	@Enumerated(EnumType.STRING)
	private PeriodicidadeEnum periodicidade;
	
	@OneToMany(mappedBy = "periodico", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Fasciculo> fasciculos;

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
}