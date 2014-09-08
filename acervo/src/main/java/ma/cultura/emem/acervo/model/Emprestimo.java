package ma.cultura.emem.acervo.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Emprestimo extends RootEntity {
	private static final long serialVersionUID = 1485590236796946485L;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataEmprestimo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataPrevista;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataDevolucao;
	
	public Emprestimo() {
		if (dataPrevista == null) {
			// data prevista de entrega, até as 18h do mesmo dia.
			dataPrevista = Calendar.getInstance();
			dataPrevista.set(Calendar.HOUR_OF_DAY, 18);
			dataPrevista.set(Calendar.MINUTE, 0);
			dataPrevista.set(Calendar.SECOND, 0);
		}
	}
	
	@Column(length = 256)
	private String observacao;
	
	@ManyToOne
	private Exemplar exemplar;
	
	@ManyToOne
	private Usuario usuario;
	
	public Calendar getDataEmprestimo() {
		return dataEmprestimo;
	}
	
	public void setDataEmprestimo(Calendar dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	
	public Calendar getDataPrevista() {
		return dataPrevista;
	}
	
	public void setDataPrevista(Calendar dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	
	public Calendar getDataDevolucao() {
		return dataDevolucao;
	}
	
	public void setDataDevolucao(Calendar dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Exemplar getExemplar() {
		return exemplar;
	}
	
	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}