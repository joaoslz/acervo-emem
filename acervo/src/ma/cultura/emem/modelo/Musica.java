package ma.cultura.emem.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ma.cultura.emem.modelo.auxiliar.Compositor;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Musica.findAll", query = "from Musica")
})
@IdClass(MusicaId.class)
public class Musica implements Serializable {

	private static final long serialVersionUID = -3083428134975977541L;

	@Id
	private int faixa;
	private String titulo;
	private int duracao;
	
	@Id
	@ManyToOne
	@JoinColumn(name="cd_id")
	private CD cd;

	@ManyToMany
	private List<Compositor> compositores;
	
	public int getFaixa() {
		return faixa;
	}

	public void setFaixa(int faixa) {
		this.faixa = faixa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public CD getCd() {
		return cd;
	}

	public void setCd(CD cd) {
		this.cd = cd;
	}

	public List<Compositor> getCompositores() {
		return compositores;
	}

	public void setCompositores(List<Compositor> compositores) {
		this.compositores = compositores;
	}
}