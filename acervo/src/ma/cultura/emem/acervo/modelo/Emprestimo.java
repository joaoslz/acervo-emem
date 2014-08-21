package ma.cultura.emem.acervo.modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Emprestimo extends BaseEntity {

	private static final long serialVersionUID = 2229058371992416087L;

	@Id
	@GeneratedValue
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataEmprestimo;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataPrevista;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataDevolucao;

	@Column(length = 256)
	private String observacao;

	@ManyToOne
	private Exemplar exemplar;

	@ManyToOne
	private Usuario usuario;

	@Override
	public Integer getId() {
		return id;
	}

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

	public void setId(Integer id) {
		this.id = id;
	}
	
}