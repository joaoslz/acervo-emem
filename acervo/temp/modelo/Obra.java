package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the obra database table.
 * 
 */
@Entity
@NamedQuery(name="Obra.findAll", query="SELECT o FROM Obra o")
public class Obra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_acervo_id")
	private int itemAcervoId;

	private short ano;

	private String classificacao;

	private String cutter;

	private short edicao;

	private byte ehIlustrado;

	private String isbn;

	private short numPaginas;

	private String serie;

	private String tipo;

	private short volume;

	//bi-directional one-to-one association to ItemAcervo
	@OneToOne
	@JoinColumn(name="item_acervo_id")
	private ItemAcervo itemAcervo;

	//bi-directional many-to-one association to ObraAutor
	@OneToMany(mappedBy="obra")
	private List<ObraAutor> obraAutors;

	public Obra() {
	}

	public int getItemAcervoId() {
		return this.itemAcervoId;
	}

	public void setItemAcervoId(int itemAcervoId) {
		this.itemAcervoId = itemAcervoId;
	}

	public short getAno() {
		return this.ano;
	}

	public void setAno(short ano) {
		this.ano = ano;
	}

	public String getClassificacao() {
		return this.classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getCutter() {
		return this.cutter;
	}

	public void setCutter(String cutter) {
		this.cutter = cutter;
	}

	public short getEdicao() {
		return this.edicao;
	}

	public void setEdicao(short edicao) {
		this.edicao = edicao;
	}

	public byte getEhIlustrado() {
		return this.ehIlustrado;
	}

	public void setEhIlustrado(byte ehIlustrado) {
		this.ehIlustrado = ehIlustrado;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public short getNumPaginas() {
		return this.numPaginas;
	}

	public void setNumPaginas(short numPaginas) {
		this.numPaginas = numPaginas;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public short getVolume() {
		return this.volume;
	}

	public void setVolume(short volume) {
		this.volume = volume;
	}

	public ItemAcervo getItemAcervo() {
		return this.itemAcervo;
	}

	public void setItemAcervo(ItemAcervo itemAcervo) {
		this.itemAcervo = itemAcervo;
	}

	public List<ObraAutor> getObraAutors() {
		return this.obraAutors;
	}

	public void setObraAutors(List<ObraAutor> obraAutors) {
		this.obraAutors = obraAutors;
	}

	public ObraAutor addObraAutor(ObraAutor obraAutor) {
		getObraAutors().add(obraAutor);
		obraAutor.setObra(this);

		return obraAutor;
	}

	public ObraAutor removeObraAutor(ObraAutor obraAutor) {
		getObraAutors().remove(obraAutor);
		obraAutor.setObra(null);

		return obraAutor;
	}

}