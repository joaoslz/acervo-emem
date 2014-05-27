package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the exemplar database table.
 * 
 */
@Entity
@NamedQuery(name="Exemplar.findAll", query="SELECT e FROM Exemplar e")
public class Exemplar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date dataAquisicao;

	private byte ehDoacao;

	@Column(name="obra_id")
	private int obraId;

	@Lob
	private String observacao;

	//bi-directional many-to-one association to Fasciculo
	@ManyToOne
	private Fasciculo fasciculo;

	//bi-directional many-to-one association to ItemAcervo
	@ManyToOne
	@JoinColumn(name="item_acervo_id")
	private ItemAcervo itemAcervo;

	public Exemplar() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataAquisicao() {
		return this.dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public byte getEhDoacao() {
		return this.ehDoacao;
	}

	public void setEhDoacao(byte ehDoacao) {
		this.ehDoacao = ehDoacao;
	}

	public int getObraId() {
		return this.obraId;
	}

	public void setObraId(int obraId) {
		this.obraId = obraId;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Fasciculo getFasciculo() {
		return this.fasciculo;
	}

	public void setFasciculo(Fasciculo fasciculo) {
		this.fasciculo = fasciculo;
	}

	public ItemAcervo getItemAcervo() {
		return this.itemAcervo;
	}

	public void setItemAcervo(ItemAcervo itemAcervo) {
		this.itemAcervo = itemAcervo;
	}

}