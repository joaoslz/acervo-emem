package ma.cultura.emem.acervo.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ma.cultura.emem.acervo.model.auxiliar.Compositor;

@Entity
public class Musica extends RootEntity {
	
	private static final long serialVersionUID = 696174448865785939L;
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
		if (compositores == null) {
			compositores = new ArrayList<>();
		}
		return compositores;
	}
	
	public void setCompositores(List<Compositor> compositores) {
		this.compositores = compositores;
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
		if(duracao == null)
			return "00:00";
		return new SimpleDateFormat("mm:ss").format(duracao);
	}
	
	@Override
	public String toString() {
		return faixa + " - " + titulo + " (" + getDuracaoToString() + ")";
	}
}