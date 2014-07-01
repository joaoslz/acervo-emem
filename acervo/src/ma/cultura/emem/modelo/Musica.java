package ma.cultura.emem.modelo;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ma.cultura.emem.modelo.auxiliar.Compositor;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Musica.findAll", query = "from Musica")
})
//@IdClass(MusicaId.class)
public class Musica implements Serializable {

	private static final long serialVersionUID = -3083428134975977541L;

	@Id
	@GeneratedValue
	private Integer id;
	private Integer faixa;
	private String titulo;
	
	@Temporal(TemporalType.TIME)
	private Date duracao;
	
//	@Id
	@ManyToOne
	@JoinColumn(name="cd_id")
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
		if(compositores == null)
			compositores = new ArrayList<>();
		return compositores;
	}

	public void setCompositores(List<Compositor> compositores) {
		this.compositores = compositores;
	}

	@Override
	public String toString() {
		return faixa + " - " + titulo + " (" + getDuracaoToString() + ")";
	}

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
	
	public String getDuracaoToString(){
		SimpleDateFormat f = new SimpleDateFormat("mm:ss");
		return f.format(duracao);
	}
}