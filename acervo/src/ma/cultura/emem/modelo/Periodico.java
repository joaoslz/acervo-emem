package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "periodico")
@NamedQueries({ @NamedQuery(name = Periodico.NAMED_QUERY_LISTAR_TODOS, query = "from Periodico p order by p.id desc") })
public class Periodico extends Obra {

	public static final String NAMED_QUERY_LISTAR_TODOS = "Periodico.listarTodos";

	private static final long serialVersionUID = -112232541131788319L;

	private String issn;

	private Boolean ehAssinado;

	@Enumerated(EnumType.STRING)
	private PeriodicidadeEnum periodicidade;
	
	@OneToMany(mappedBy = "periodico")
	private List<Fasciculo> fasciculos = new ArrayList<>();

	
	public Periodico() {
		// TODO Auto-generated constructor stub
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

	/**
	 * @return the periodicidade
	 */
	public PeriodicidadeEnum getPeriodicidade() {
		return periodicidade;
	}

	/**
	 * @param periodicidade the periodicidade to set
	 */
	public void setPeriodicidade(PeriodicidadeEnum periodicidade) {
		this.periodicidade = periodicidade;
	}

	public List<Fasciculo> getFasciculos() {
		return fasciculos;
	}

	public void setFasciculos(List<Fasciculo> fasciculos) {
		this.fasciculos = fasciculos;
	}
	

}