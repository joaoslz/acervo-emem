package ma.cultura.emem.acervo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Exemplar extends RootEntity {
	
	private static final long serialVersionUID = 8689410871717711520L;
	
	private boolean ehDoacao;
	private boolean disponivel;
	
	// @Past(message="A data de aquisição deve ser anterior a data atual!")
	@Temporal(TemporalType.DATE)
	private Date dataAquisicao;
	
	@Column(columnDefinition = "text")
	private String observacao;
	
	@ManyToOne
	private ItemAcervo itemAcervo;
	
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
	
	public Date getDataAquisicao() {
		return dataAquisicao;
	}
	
	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}
		
	public ItemAcervo getItemAcervo() {
		return itemAcervo;
	}
	
	public void setItemAcervo(ItemAcervo itemAcervo) {
		this.itemAcervo = itemAcervo;
	}
	
	public boolean isDisponivel() {
		return disponivel;
	}
	
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	@Override
	public String toString() {
		return getId() + " - " + (itemAcervo == null ? "NULL" : itemAcervo.toString());
	}
}