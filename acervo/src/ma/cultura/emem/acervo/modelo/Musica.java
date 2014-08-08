package ma.cultura.emem.acervo.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ma.cultura.emem.acervo.modelo.auxiliar.Compositor;

@Entity
public class Musica extends BaseEntity {

	private static final long serialVersionUID = -3083428134975977541L;

	@Id
	@GeneratedValue
	private Integer id;
	private Integer faixa;
	private String titulo;

	@Temporal(TemporalType.TIME)
	private Date duracao;

	@ManyToOne
	@JoinColumn(name = "cd_id")
	private CD cd;

	@ManyToMany
	private List<Compositor> compositores = new ArrayList<Compositor>();

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public CD getCd() {
		return cd;
	}

	public void setCd(CD cd) {
		this.cd = cd;
	}

	public List<Compositor> getCompositores() {
		if (compositores == null)
			compositores = new ArrayList<>();
		return compositores;
	}

	public void setCompositores(List<Compositor> compositores) {
		this.compositores = compositores;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFaixa() {
		return faixa;
	}

	public void setFaixa(Integer faixa) {
		this.faixa = faixa;
	}

	public Date getDuracao() {
		return duracao;
	}

	public void setDuracao(Date duracao) {
		this.duracao = duracao;
	}

	public String getDuracaoToString() {
		final SimpleDateFormat f = new SimpleDateFormat("mm:ss");
		return f.format(duracao);
	}

	@Override
	public String toString() {
		return faixa + " - " + titulo + " (" + getDuracaoToString() + ")";
	}
}