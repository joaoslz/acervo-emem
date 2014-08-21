package ma.cultura.emem.acervo.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Exemplar extends BaseEntity {

	private static final long serialVersionUID = 8689410871717711520L;

	@Id
	@GeneratedValue
	private Integer id;

	private boolean ehDoacao;
	private boolean disponivel;

//	@Past(message="A data de aquisição deve ser anterior a data atual!")
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
			dataAquisicao = Calendar.getInstance();
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

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
}