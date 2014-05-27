package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the midia database table.
 * 
 */
@Entity
@NamedQuery(name="Midia.findAll", query="SELECT m FROM Midia m")
public class Midia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_acervo_id")
	private int itemAcervoId;

	@Temporal(TemporalType.DATE)
	private Date ano;

	@Temporal(TemporalType.DATE)
	private Date anoEdicao;

	private String comentario;

	private String duracao;

	@Column(name="gravadora_id")
	private int gravadoraId;

	private short numMusicas;

	//bi-directional one-to-one association to ItemAcervo
	@OneToOne
	@JoinColumn(name="item_acervo_id")
	private ItemAcervo itemAcervo;

	//bi-directional many-to-one association to Tipomidia
	@ManyToOne
	private Tipomidia tipomidia;

	//bi-directional many-to-one association to Musica
	@OneToMany(mappedBy="midia")
	private List<Musica> musicas;

	public Midia() {
	}

	public int getItemAcervoId() {
		return this.itemAcervoId;
	}

	public void setItemAcervoId(int itemAcervoId) {
		this.itemAcervoId = itemAcervoId;
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

	public String getDuracao() {
		return this.duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
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

	public ItemAcervo getItemAcervo() {
		return this.itemAcervo;
	}

	public void setItemAcervo(ItemAcervo itemAcervo) {
		this.itemAcervo = itemAcervo;
	}

	public Tipomidia getTipomidia() {
		return this.tipomidia;
	}

	public void setTipomidia(Tipomidia tipomidia) {
		this.tipomidia = tipomidia;
	}

	public List<Musica> getMusicas() {
		return this.musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public Musica addMusica(Musica musica) {
		getMusicas().add(musica);
		musica.setMidia(this);

		return musica;
	}

	public Musica removeMusica(Musica musica) {
		getMusicas().remove(musica);
		musica.setMidia(null);

		return musica;
	}

}