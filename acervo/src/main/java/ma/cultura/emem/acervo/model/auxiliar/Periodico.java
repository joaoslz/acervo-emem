package ma.cultura.emem.acervo.model.auxiliar;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ma.cultura.emem.acervo.model.Fasciculo;
import ma.cultura.emem.acervo.model.auxiliar.enums.PeriodicidadeEnum;

@Entity
public class Periodico extends AuxiliarEntity {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -2972945691254455133L;
	
	@Column(length = 20)
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
		if (fasciculos == null) {
			fasciculos = new ArrayList<>();
		}
		getFasciculos().add(fasciculo);
		fasciculo.setPeriodico(this);
		
		return fasciculo;
	}
	
	public Fasciculo removeFasciculo(Fasciculo fasciculo) {
		getFasciculos().remove(fasciculo);
		fasciculo.setPeriodico(null);
		return fasciculo;
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