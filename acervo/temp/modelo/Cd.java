package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cd database table.
 * 
 */
@Entity
@NamedQuery(name="Cd.findAll", query="SELECT c FROM Cd c")
public class Cd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date ano;

	@Temporal(TemporalType.DATE)
	private Date anoEdicao;

	private String comentario;

	@Temporal(TemporalType.DATE)
	private Date dataAquisicao;

	private String duracao;

	private byte ehDoacao;

	@Column(name="gravadora_id")
	private int gravadoraId;

	private short numMusicas;

	private short quantidade;

	private String titulo;

	//bi-directional many-to-many association to Cantor
	@ManyToMany(mappedBy="cds")
	private List<Cantor> cantors;

	//bi-directional many-to-one association to Local
	@ManyToOne
	private Local local;

	//bi-directional many-to-one association to Tipomidia
	@ManyToOne
	@JoinColumn(name="midia_nome")
	private Tipomidia tipomidia;

	//bi-directional many-to-one association to Idioma
	@ManyToOne
	private Idioma idioma;

	public Cd() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAno() {
		return this.ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public Date getAnoEdicao() {
		return this.anoEdicao;
	}

	public void setAnoEdicao(Date anoEdicao) {
		this.anoEdicao = anoEdicao;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getDataAquisicao() {
		return this.dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public String getDuracao() {
		return this.duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public byte getEhDoacao() {
		return this.ehDoacao;
	}

	public void setEhDoacao(byte ehDoacao) {
		this.ehDoacao = ehDoacao;
	}

	public int getGravadoraId() {
		return this.gravadoraId;
	}

	public void setGravadoraId(int gravadoraId) {
		this.gravadoraId = gravadoraId;
	}

	public short getNumMusicas() {
		return this.numMusicas;
	}

	public void setNumMusicas(short numMusicas) {
		this.numMusicas = numMusicas;
	}

	public short getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(short quantidade) {
		this.quantidade = quantidade;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Cantor> getCantors() {
		return this.cantors;
	}

	public void setCantors(List<Cantor> cantors) {
		this.cantors = cantors;
	}

	public Local getLocal() {
		return this.local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Tipomidia getTipomidia() {
		return this.tipomidia;
	}

	public void setTipomidia(Tipomidia tipomidia) {
		this.tipomidia = tipomidia;
	}

	public Idioma getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

}