package ma.cultura.emem.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
		@NamedQuery(name = "Exemplar.findAll", query = "from Exemplar e order by e.id desc"),
		@NamedQuery(name = "Exemplar.listarPorItemAcervo", query = "from Exemplar e where e.itemAcervo.id = :idItemAcervo") 
})
public class Exemplar extends BaseEntity {

	private static final long serialVersionUID = 8689410871717711520L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private boolean ehDoacao;
	@Temporal(TemporalType.DATE)
	private Calendar dataAquisicao;

	@Column(columnDefinition = "text")
	private String observacao;

	@ManyToOne
	private ItemAcervo itemAcervo;

	@ManyToOne
	private Fasciculo fasciculo;

	public boolean isEhDoacao() {
		return ehDoacao;
	}

	public void setEhDoacao(boolean ehDoacao) {
		this.ehDoacao = ehDoacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Calendar getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Calendar dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public void setDataAquisicao(Date d) {
		if (d != null) {
			dataAquisicao = GregorianCalendar.getInstance();
			dataAquisicao.setTime(d);
		}
	}

	public ItemAcervo getItemAcervo() {
		return itemAcervo;
	}

	public void setItemAcervo(ItemAcervo itemAcervo) {
		this.itemAcervo = itemAcervo;
	}

	public Fasciculo getFasciculo() {
		return fasciculo;
	}

	public void setFasciculo(Fasciculo fasciculo) {
		this.fasciculo = fasciculo;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}